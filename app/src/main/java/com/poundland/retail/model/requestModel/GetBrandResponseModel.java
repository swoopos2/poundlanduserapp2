package com.poundland.retail.model.requestModel;

import java.util.List;

public class GetBrandResponseModel {

    /**
     * status : true
     * message : list of brands
     * filter : {"filter_type":"Brand","isMultiple":1,"filter_list":[{"filter_value":"3","display_name":"Federico Mahora"},{"filter_value":"4","display_name":"Nike"},{"filter_value":"5","display_name":"Adidas"},{"filter_value":"6","display_name":"Converse"},{"filter_value":"7","display_name":"Fila"},{"filter_value":"8","display_name":"Puma"},{"filter_value":"12","display_name":"Benq"},{"filter_value":"16","display_name":"Sony"}]}
     * modifiers : {"filter_type":"Modifier","isMultiple":1,"filter_list":[{"display_name":"111","filter_value":"111"},{"display_name":"kkk","filter_value":"kkk"},{"display_name":"L,Black","filter_value":"L,Black"},{"display_name":"L,Blue","filter_value":"L,Blue"},{"display_name":"large","filter_value":"large"},{"display_name":"M,Black","filter_value":"M,Black"},{"display_name":"M,Blue","filter_value":"M,Blue"},{"display_name":"medium","filter_value":"medium"},{"display_name":"small","filter_value":"small"},{"display_name":"xl","filter_value":"xl"},{"display_name":"Black","filter_value":"Black"},{"display_name":"Blue","filter_value":"Blue"},{"display_name":"L,Black","filter_value":"L,Black"},{"display_name":"L,Red","filter_value":"L,Red"},{"display_name":"Large","filter_value":"Large"},{"display_name":"M,Black","filter_value":"M,Black"},{"display_name":"M,Red","filter_value":"M,Red"},{"display_name":"Medium","filter_value":"Medium"},{"display_name":"Red","filter_value":"Red"},{"display_name":"S,Black","filter_value":"S,Black"},{"display_name":"S,Red","filter_value":"S,Red"},{"display_name":"Small","filter_value":"Small"},{"display_name":"XL,Black","filter_value":"XL,Black"},{"display_name":"XL,Red","filter_value":"XL,Red"},{"display_name":"XS,Black","filter_value":"XS,Black"},{"display_name":"XS,Red","filter_value":"XS,Red"},{"display_name":"11","filter_value":"11"}]}
     */
    private boolean status;
    private String message;
    private FilterBean filter;
    private ModifiersBean modifiers;


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public FilterBean getFilter() {
        return filter;
    }

    public void setFilter(FilterBean filter) {
        this.filter = filter;
    }

    public ModifiersBean getModifiers() {
        return modifiers;
    }

    public void setModifiers(ModifiersBean modifiers) {
        this.modifiers = modifiers;
    }
    public static class FilterBean {
        /**
         * filter_type : Brand
         * isMultiple : 1
         * filter_list : [{"filter_value":"1","display_name":"Shoes"},{"filter_value":"2","display_name":"Levis"}]
         */

        private String filter_type;
        private int isMultiple;
        private List<FilterListBean> filter_list;

        public String getFilter_type() {
            return filter_type;
        }

        public void setFilter_type(String filter_type) {
            this.filter_type = filter_type;
        }

        public int getIsMultiple() {
            return isMultiple;
        }

        public void setIsMultiple(int isMultiple) {
            this.isMultiple = isMultiple;
        }

        public List<FilterListBean> getFilter_list() {
            return filter_list;
        }

        public void setFilter_list(List<FilterListBean> filter_list) {
            this.filter_list = filter_list;
        }

        public static class FilterListBean {
            /**
             * filter_value : 1
             * display_name : Shoes
             */

            private String filter_value;
            private String display_name;

            public String getFilter_value() {
                return filter_value;
            }

            public void setFilter_value(String filter_value) {
                this.filter_value = filter_value;
            }

            public String getDisplay_name() {
                return display_name;
            }

            public void setDisplay_name(String display_name) {
                this.display_name = display_name;
            }
        }
    }
    public static class ModifiersBean {
        /**
         * filter_type : Modifier
         * isMultiple : 1
         * filter_list : [{"display_name":"111","filter_value":"111"},{"display_name":"kkk","filter_value":"kkk"},{"display_name":"L,Black","filter_value":"L,Black"},{"display_name":"L,Blue","filter_value":"L,Blue"},{"display_name":"large","filter_value":"large"},{"display_name":"M,Black","filter_value":"M,Black"},{"display_name":"M,Blue","filter_value":"M,Blue"},{"display_name":"medium","filter_value":"medium"},{"display_name":"small","filter_value":"small"},{"display_name":"xl","filter_value":"xl"},{"display_name":"Black","filter_value":"Black"},{"display_name":"Blue","filter_value":"Blue"},{"display_name":"L,Black","filter_value":"L,Black"},{"display_name":"L,Red","filter_value":"L,Red"},{"display_name":"Large","filter_value":"Large"},{"display_name":"M,Black","filter_value":"M,Black"},{"display_name":"M,Red","filter_value":"M,Red"},{"display_name":"Medium","filter_value":"Medium"},{"display_name":"Red","filter_value":"Red"},{"display_name":"S,Black","filter_value":"S,Black"},{"display_name":"S,Red","filter_value":"S,Red"},{"display_name":"Small","filter_value":"Small"},{"display_name":"XL,Black","filter_value":"XL,Black"},{"display_name":"XL,Red","filter_value":"XL,Red"},{"display_name":"XS,Black","filter_value":"XS,Black"},{"display_name":"XS,Red","filter_value":"XS,Red"},{"display_name":"11","filter_value":"11"}]
         */

        private String filter_type;
        private int isMultiple;
        private List<FilterListModifierBean> filter_list;

        public String getFilter_type() {
            return filter_type;
        }

        public void setFilter_type(String filter_type) {
            this.filter_type = filter_type;
        }

        public int getIsMultiple() {
            return isMultiple;
        }

        public void setIsMultiple(int isMultiple) {
            this.isMultiple = isMultiple;
        }

        public List<FilterListModifierBean> getFilter_list() {
            return filter_list;
        }

        public void setFilter_list(List<FilterListModifierBean> filter_list) {
            this.filter_list = filter_list;
        }

        public static class FilterListModifierBean {
            /**
             * display_name : 111
             * filter_value : 111
             */

            private String display_name;
            private String filter_value;

            public String getDisplay_name() {
                return display_name;
            }

            public void setDisplay_name(String display_name) {
                this.display_name = display_name;
            }

            public String getFilter_value() {
                return filter_value;
            }

            public void setFilter_value(String filter_value) {
                this.filter_value = filter_value;
            }
        }
    }

}
