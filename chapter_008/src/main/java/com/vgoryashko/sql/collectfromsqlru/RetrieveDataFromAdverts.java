package com.vgoryashko.sql.collectfromsqlru;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.time.LocalDate;
import java.util.regex.Pattern;

/**
 * Class that retrieves data from Document.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 11/20/17
 */
public class RetrieveDataFromAdverts {

    private final Logger logger = LogManager.getLogger(this.getClass().getName());

    private Document document;

    private LocalDate lastStartTime;
    
    private boolean firstStart;

    public RetrieveDataFromAdverts(Document document, LocalDate lastStartTime, boolean firstStart) {
        this.document = document;
        this.lastStartTime = lastStartTime;
        this.firstStart = firstStart;
    }

    public Advertisement retrieveAdvertData(Element link) {

        Advertisement advertisement = null;

        FetchPage fetchPage = new FetchPage();

        String href;

        if (!link.hasClass("pages") & !link.hasClass("newTopic") & !link.hasClass("closedTopic")) {

            href = link.attr("href");
            fetchPage.setUrl(href);

            Document doc = fetchPage.fetch();

            Elements messageHeaders = doc.getElementsByClass("messageHeader");

            Pattern pattern = Pattern.compile(
                    "(.*Java ?Script)|\1.?Angular [Dd]ev.*|.*QA.*|.*Android.*|.*PHP.*|.*Python.*|.*[Пп]репод.*");

            if (!new TestMatch(messageHeaders.get(0).text()).test(pattern)) {

                pattern = Pattern.compile(
                        "(.*[Рр]азработчики?|.*[Пп]рограммисты?) +([Jj]ava) *([Ss][Ee].[Ee][Ee])?.*|"
                                + ".*([Jj]ava) *([Ss][Ee].[Ee][Ee])?(.*[Рр]азработчики?|.*[Пп]рограммисты?).*|"
                                + "(.*[Jj]ava) +([Ww]eb)(( +[Dd]ev)| +([Dd]eveloper)).*");

                Elements messageBodies = doc.getElementsByClass("msgBody");

                Element messageBody = messageBodies.get(1);

                String advertText = messageBody.text();

                if (new TestMatch(advertText).test(pattern)) {

                    messageHeaders = doc.getElementsByClass("messageHeader");
                    Elements messageFooters = doc.getElementsByClass("msgFooter");

                    String[] messageFooterSplit = messageFooters.get(0).text().split(",?\\h+");
                    String[] time = messageFooterSplit[3].split(":");

                    advertisement = new Advertisement();

                    advertisement.setHref(href);
                    advertisement.setHeader(messageHeaders.get(0).text());
                    advertisement.setDescription(advertText);
                    advertisement.setDate(String.format("%s %s %s",
                            messageFooterSplit[0],
                            messageFooterSplit[1],
                            messageFooterSplit[2])
                    );
                }
            }
        }

        return advertisement;

    }

    public boolean retrieveAdvert() {

        boolean stop = false;

        ConvertDate convertDate;

        LocalDate advertDate;

        Elements elements = this.document.getElementsByTag("tr");

        int occasionCounter = 0;

        for (Element trTag : elements) {

            if (stop) {
                break;
            }

            Elements trTagChildren = trTag.children();

            for (Element trTagChild : trTagChildren) {

                if (stop) {
                    break;
                }

                if (trTagChild.hasClass("postslisttopic")) {

                    Elements trTagChildChildren = trTagChild.children();

                    for (Element trTagChildChildrenChild : trTagChildChildren) {

                        if (stop) {
                            break;
                        }

                        Elements links = trTagChildChildrenChild.getElementsByTag("a");

                        if (links.size() > 0) {

                            for (Element link : links) {

                                Advertisement advertisement = retrieveAdvertData(link);

                                if (advertisement != null) {
                                    logger.trace("Advert found.");

                                    convertDate = new ConvertDate(advertisement.getDate().split(" "));

                                    int[] advertDateParsed = convertDate.convert();

                                    advertDate = LocalDate.of(advertDateParsed[2] + 2000, advertDateParsed[1], advertDateParsed[0]);

                                    if (this.firstStart && advertDate.isAfter(LocalDate.of(lastStartTime.getYear(), 1, 1))) {
                                        logger.debug(String.format("Advert date is : %s", advertDate.toString()));
                                        new UpdateDataBase().insert(advertisement);
                                    } else if (advertDate.isAfter(this.lastStartTime) || advertDate.equals(this.lastStartTime)) {
                                        new UpdateDataBase().insert(advertisement);
                                        occasionCounter = 0;
                                    } else {
                                        if (++occasionCounter > 4) {
                                            stop = true;
                                            logger.debug("There are no new adverts.");
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return stop;
    }
}
