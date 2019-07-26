package com.fpt.entity;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

import java.util.Calendar;

@Entity
public class CrawlerSource {
@Id
    private long id;
//thong tin chung
    @Index
    private String url;
    @Index
    private long categoryId;
    private String linkSelector;
    //lay chi tiet
    private String titleSelector;
    private String descriptionSelector;
    private String contentSelector;

    @Index
    private long createdAtMLS;
    @Index
    private long updatedAtMLS;
    private long deleteaAtMLS;
    @Index
    private int status; //1 active 2 deactive

    public CrawlerSource() {
        this.id = Calendar.getInstance().getTimeInMillis();
        this.createdAtMLS = Calendar.getInstance().getTimeInMillis();
        this.updatedAtMLS = Calendar.getInstance().getTimeInMillis();
    }

    public long getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public String getLinkSelector() {
        return linkSelector;
    }

    public String getTitleSelector() {
        return titleSelector;
    }

    public String getDescriptionSelector() {
        return descriptionSelector;
    }

    public String getContentSelector() {
        return contentSelector;
    }

    public long getCreatedAtMLS() {
        return createdAtMLS;
    }

    public long getUpdatedAtMLS() {
        return updatedAtMLS;
    }

    public long getDeleteaAtMLS() {
        return deleteaAtMLS;
    }

    public int getStatus() {
        return status;
    }

    public static final class Builder {
        //thong tin chung
            private String url;
        private String linkSelector;
        //lay chi tiet
        private String titleSelector;
        private String descriptionSelector;
        private String contentSelector;
        private int status; //1 active 2 deactive

        private Builder() {
        }

        public static Builder aCrawlerSource() {
            return new Builder();
        }

        public Builder withUrl(String url) {
            this.url = url;
            return this;
        }

        public Builder withLinkSelector(String linkSelector) {
            this.linkSelector = linkSelector;
            return this;
        }

        public Builder withTitleSelector(String titleSelector) {
            this.titleSelector = titleSelector;
            return this;
        }

        public Builder withDescriptionSelector(String descriptionSelector) {
            this.descriptionSelector = descriptionSelector;
            return this;
        }

        public Builder withContentSelector(String contentSelector) {
            this.contentSelector = contentSelector;
            return this;
        }

        public Builder withStatus(int status) {
            this.status = status;
            return this;
        }

        public CrawlerSource build() {
            CrawlerSource crawlerSource = new CrawlerSource();
            crawlerSource.status = this.status;
            crawlerSource.url = this.url;
            crawlerSource.descriptionSelector = this.descriptionSelector;
            crawlerSource.linkSelector = this.linkSelector;
            crawlerSource.contentSelector = this.contentSelector;
            crawlerSource.titleSelector = this.titleSelector;
            return crawlerSource;
        }
    }
}
