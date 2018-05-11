package com.vgoryashko.sql.collectfromsqlru;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;

/**
 * Class that checks eligibility of an advert to be inserted into database.
 *
 * @author Vlad Goryashko
 * @version 0.6
 * @since 11/29/17
 */
public class CheckEligibility {

    private final Logger logger = LogManager.getLogger(this.getClass());

    private Advertisement advertisement;

    private LocalDate advertDate;

    private LocalDate lastUpdateTime;

    private boolean firstStart;

    private boolean eligible = false;

    public CheckEligibility() {
    }

    public CheckEligibility(Advertisement advertisement, LocalDate advertDate, LocalDate lastUpdateTime, boolean firstStart) {
        this.advertisement = advertisement;
        this.advertDate = advertDate;
        this.lastUpdateTime = lastUpdateTime;
        this.firstStart = firstStart;
    }

    public boolean check() {

        if (advertisement != null) {
            if (this.firstStart && advertDate.isAfter(LocalDate.of(lastUpdateTime.getYear(), 1, 1))) {
                eligible = true;
            } else if (advertDate.isAfter(this.lastUpdateTime) || advertDate.equals(this.lastUpdateTime)) {
                eligible = true;
            } else {
                eligible = false;
            }
        }

        return eligible;
    }

    public void setAdvertisement(Advertisement advertisement) {
        this.advertisement = advertisement;
    }

    public void setAdvertDate(LocalDate advertDate) {
        this.advertDate = advertDate;
    }

    public void setLastUpdateTime(LocalDate lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public void setFirstStart(boolean firstStart) {
        this.firstStart = firstStart;
    }

    public void setEligible(boolean eligible) {
        this.eligible = eligible;
    }
}
