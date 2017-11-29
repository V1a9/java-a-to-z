package com.vgoryashko.sql.collectfromsqlru;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.util.regex.Pattern;

/**
 * Class that retrieves an advert on the link passed to.
 *
 * @author Vlad Goryashko
 * @version 0.6
 * @since 11/29/17
 */
public class RetrieveAdvert {
    
    private Element link;
    private FetchPage fetchPage;

    public RetrieveAdvert() {
    }

    public RetrieveAdvert(Element link, FetchPage fetchPage) {
        this.link = link;
        this.fetchPage = fetchPage;
    }

    public Advertisement retrieveAdvert() {

        Advertisement advertisement = null;

        String href;

        if (!this.link.hasClass("pages") & !this.link.hasClass("newTopic") & !this.link.hasClass("closedTopic")) {

            href = this.link.attr("href");
            this.fetchPage.setUrl(href);

            Document doc = this.fetchPage.fetch();

            Elements messageHeaders = doc.getElementsByClass("messageHeader");

            Pattern pattern = Pattern.compile(
                    "(.*Java ?Script)|\1.?Angular [Dd]ev.*|.*QA.*|.*Android.*|.*PHP.*|.*Python.*|.*[Пп]репод.*");

            if (!new TestMatch(messageHeaders.get(0).text()).test(pattern)) {

                pattern = Pattern.compile(
                        "(.*[Рр]азработчики?|.*[Пп]рограммисты?) +([Jj]ava) *([Ss][Ee].[Ee][Ee])?.*|"
                                + ".*([Jj]ava) *([Ss][Ee].[Ee][Ee])?(.*[Рр]азработчики?|.*[Пп]рограммисты?).*|"
                                + "(.*[Jj]ava) +([Ww]eb)(( +[Dd]ev)| +([Dd]eveloper)).*");

                Elements messageBodies = doc.getElementsByClass("msgBody");

                org.jsoup.nodes.Element messageBody = messageBodies.get(1);

                String advertText = messageBody.text();

                if (new TestMatch(advertText).test(pattern)) {

                    messageHeaders = doc.getElementsByClass("messageHeader");
                    Elements messageFooters = doc.getElementsByClass("msgFooter");

                    String[] messageFooterSplit = messageFooters.get(0).text().split(",?\\h+");

                    String[] advertDate = new String[]{messageFooterSplit[0], messageFooterSplit[1], messageFooterSplit[2]};

                    int[] advertDateParsed = new ConvertDate(advertDate).convert();

                    advertisement = new Advertisement();

                    advertisement.setHref(href);
                    advertisement.setHeader(messageHeaders.get(0).text());
                    advertisement.setDescription(advertText);
                    advertisement.setDate(String.format("%s/%s/%s",
                            advertDateParsed[0],
                            advertDateParsed[1],
                            advertDateParsed[2]
                            )
                    );
                }
            }
        }

        return advertisement;
    }

    public void setLink(Element link) {
        this.link = link;
    }

    public void setFetchPage(FetchPage fetchPage) {
        this.fetchPage = fetchPage;
    }
}
