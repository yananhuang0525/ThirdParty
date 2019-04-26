package com.panku.thirdparty.selectcity;

import java.util.List;

/**
 * data 2019/4/25
 * time 16:46
 * Created by huangyanan.
 */
public class CityModel {

    /**
     * code : 410000
     * name : 河南省
     * children : [{"code":"410100","name":"郑州市","children":[{"code":"410102","name":"中原区"},{"code":"410103","name":"二七区"},{"code":"410104","name":"管城回族区"},{"code":"410105","name":"金水区"},{"code":"410106","name":"上街区"},{"code":"410108","name":"惠济区"},{"code":"410122","name":"中牟县"},{"code":"410181","name":"巩义市"},{"code":"410182","name":"荥阳市"},{"code":"410183","name":"新密市"},{"code":"410184","name":"新郑市"},{"code":"410185","name":"登封市"}]}]
     */

    private String code;
    private String name;
    private List<ChildrenBeanX> children;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ChildrenBeanX> getChildren() {
        return children;
    }

    public void setChildren(List<ChildrenBeanX> children) {
        this.children = children;
    }

    public static class ChildrenBeanX {
        /**
         * code : 410100
         * name : 郑州市
         * children : [{"code":"410102","name":"中原区"},{"code":"410103","name":"二七区"},{"code":"410104","name":"管城回族区"},{"code":"410105","name":"金水区"},{"code":"410106","name":"上街区"},{"code":"410108","name":"惠济区"},{"code":"410122","name":"中牟县"},{"code":"410181","name":"巩义市"},{"code":"410182","name":"荥阳市"},{"code":"410183","name":"新密市"},{"code":"410184","name":"新郑市"},{"code":"410185","name":"登封市"}]
         */

        private String code;
        private String name;
        private List<ChildrenBean> children;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<ChildrenBean> getChildren() {
            return children;
        }

        public void setChildren(List<ChildrenBean> children) {
            this.children = children;
        }

        public static class ChildrenBean {
            /**
             * code : 410102
             * name : 中原区
             */

            private String code;
            private String name;

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
