package com.example.myproject.module.menu;

import com.example.myproject.annotation.WxButton;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class WxButtonItem {

    @JsonProperty("sub_button")
    @JsonInclude(Include.NON_EMPTY)
    private List<WxButtonItem> subButtons = new ArrayList<>();

    @JsonIgnore
    private WxButton.Group group;

    @JsonInclude(Include.NON_NULL)
    private WxButton.Type type;

    @JsonInclude(Include.NON_NULL)
    private String name;

    @JsonIgnore
    private boolean main;

    @JsonIgnore
    private WxButton.Order order;

    @JsonInclude(Include.NON_NULL)
    private String key;

    @JsonInclude(Include.NON_NULL)
    private String url;

    @JsonInclude(Include.NON_NULL)
    @JsonProperty("mediaId")
    private String mediaId;

    public List<WxButtonItem> getSubButtons() {
        return subButtons;
    }

    public WxButton.Type getType() {
        return type;
    }

    public WxButton.Order getOrder() {
        return order;
    }

    public boolean isMain() {
        return main;
    }

    public String getName() {
        return name;
    }

    public String getKey() {
        return key;
    }

    public String getUrl() {
        return url;
    }

    public String getMediaId() {
        return mediaId;
    }

    public WxButton.Group getGroup() {
        return group;
    }

    public WxButtonItem addSubButton(WxButtonItem item) {
        this.subButtons.add(item);
        return this;
    }

    WxButtonItem() {
        super();
    }

    WxButtonItem(WxButton.Group group, WxButton.Type type, boolean main, WxButton.Order order, String name,
                 String key, String url, String mediaId) {
        super();
        this.group = group;
        this.type = type;
        this.main = main;
        this.order = order;
        this.name = name;
        this.key = key;
        this.url = url;
        this.mediaId = mediaId;
    }

    public boolean equalsBak(Object o) {
        if (this == o) return true;
        if (!(o instanceof WxButtonItem)) return false;

        WxButtonItem that = (WxButtonItem) o;

        // 子菜单数量不同，直接不相等
        if (this.getSubButtons().size() != that.getSubButtons().size()) {
            return false;
        }

        // 父菜单只比较name和子
        if (this.getSubButtons().size() > 0 && that.getSubButtons().size() > 0) {
            if (this.getSubButtons().equals(that.getSubButtons())) {
                return getName().equals(that.getName());
            }
            return false;
        }
        // 非父菜单，全部比较，要把每个类型的比较摘出来，不想摘了
        if (getType() != that.getType()) return false;
        if (!getName().equals(that.getName())) return false;
        // VIEW会自动抹掉key，所以不是VIEW的时候才判断key
        if (getType() != WxButton.Type.VIEW && (getKey() != null ? !getKey().equals(that.getKey()) : that.getKey() != null)) return false;
        if (getUrl() != null ? !getUrl().equals(that.getUrl()) : that.getUrl() != null) return false;
        return getMediaId() != null ? getMediaId().equals(that.getMediaId()) : that.getMediaId() == null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WxButtonItem)) return false;

        WxButtonItem that = (WxButtonItem) o;

        // 子菜单数量不同，直接不相等
        if (this.getSubButtons().size() != that.getSubButtons().size()) {
            return false;
        }

        // 父菜单只比较name和子
        if (this.getSubButtons().size() > 0 && that.getSubButtons().size() > 0) {
            if (this.getSubButtons().equals(that.getSubButtons())) {
                return getName().equals(that.getName());
            }
            return false;
        }
        // 非父菜单，全部比较，要把每个类型的比较摘出来，不想摘了
        if (getType() != that.getType()) return false;
        if (!getName().equals(that.getName())) return false;
        // VIEW会自动抹掉key，只有两个key都非null的时候才做下一步判断
        if (getKey() != null && that.getKey() != null && !getKey().equals(that.getKey())) return false;
        // 同上
        if (getUrl() != null && that.getUrl() != null && !getUrl().equals(that.getUrl())) return false;
        return getMediaId() == null || that.getMediaId() == null || getMediaId().equals(that.getMediaId());
    }

    @Override
    public int hashCode() {
        int result = getSubButtons() != null ? getSubButtons().hashCode() : 0;
        result = 31 * result + getGroup().hashCode();
        result = 31 * result + getType().hashCode();
        result = 31 * result + getName().hashCode();
        result = 31 * result + (isMain() ? 1 : 0);
        result = 31 * result + (getOrder() != null ? getOrder().hashCode() : 0);
        result = 31 * result + (getKey() != null ? getKey().hashCode() : 0);
        result = 31 * result + (getUrl() != null ? getUrl().hashCode() : 0);
        result = 31 * result + (getMediaId() != null ? getMediaId().hashCode() : 0);
        return result;
    }

    public static WxButtonItem.Builder create() {
        return new Builder();
    }

    public static class Builder {

        private WxButton.Type type;
        private WxButton.Group group;
        private boolean main;
        private WxButton.Order order;
        private String name;
        private String key;
        private String url;
        private String mediaId;

        Builder() {
            super();
        }

        public Builder setGroup(WxButton.Group group) {
            this.group = group;
            return this;
        }

        public Builder setType(WxButton.Type type) {
            this.type = type;
            return this;
        }

        public Builder setMain(boolean main) {
            this.main = main;
            return this;
        }

        public Builder setOrder(WxButton.Order order) {
            this.order = order;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setKey(String key) {
            this.key = StringUtils.isEmpty(key) ? null : key;
            return this;
        }

        public Builder setUrl(String url) {
            this.url = StringUtils.isEmpty(url) ? null : url;
            return this;
        }

        public Builder setMediaId(String mediaId) {
            this.mediaId = StringUtils.isEmpty(mediaId) ? null : mediaId;
            return this;
        }

        public WxButtonItem build() {
            Assert.isTrue(!StringUtils.isEmpty(name), "菜单名不能为空");
            // 判断一级菜单长度，不是main或者是main且长度小于等于16
            Assert.isTrue(!main || name.getBytes().length <= 16, "一级菜单名过长");
            // 判断二级菜单长度，是main或者不是main且长度小于等于60
            Assert.isTrue(main || name.getBytes().length <= 60, "二级菜单名过长");
            Assert.isTrue(key == null || key.getBytes().length <= 128, "key不能过长");
            Assert.notNull(type, "菜单必须有类型");
            Assert.notNull(group, "菜单必须有分组");
            Assert.isTrue(this.type != WxButton.Type.CLICK || this.key != null,
                    "click类型必须有key");
            Assert.isTrue(this.type != WxButton.Type.VIEW || this.url != null,
                    "view类型必须有url");
            Assert.isTrue((this.type != WxButton.Type.MEDIA_ID && this.type != WxButton.Type.VIEW_LIMITED) || this.mediaId != null,
                    "media_id类型和view_limited类型必须有mediaId");
            return new WxButtonItem(group, type, main, order, name, key, url, mediaId);
        }

    }

}
