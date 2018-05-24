package in.fusionbit.shreejeepricelist.apimodels;

import java.util.List;

public class ProductList {
    /**
     * company_name : Ambica Motors
     * categories : [{"category_name":"Bajaj","products":[{"period":"2018-05-01","product_name":"Discover 125","mrp":"48000","srp":"45000"},{"period":"2018-05-01","product_name":"pulsar 150","mrp":"60000","srp":"55000"}]},{"category_name":"Honda","products":[{"period":"2018-05-01","product_name":"Activa","mrp":"54000","srp":"49000"}]}]
     */

    private String company_name;
    private List<CategoriesBean> categories;

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public List<CategoriesBean> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoriesBean> categories) {
        this.categories = categories;
    }

    public static class CategoriesBean {
        /**
         * category_name : Bajaj
         * products : [{"period":"2018-05-01","product_name":"Discover 125","mrp":"48000","srp":"45000"},{"period":"2018-05-01","product_name":"pulsar 150","mrp":"60000","srp":"55000"}]
         */

        private String category_name;
        private List<ProductsBean> products;

        public String getCategory_name() {
            return category_name;
        }

        public void setCategory_name(String category_name) {
            this.category_name = category_name;
        }

        public List<ProductsBean> getProducts() {
            return products;
        }

        public void setProducts(List<ProductsBean> products) {
            this.products = products;
        }

        public static class ProductsBean {
            /**
             * period : 2018-05-01
             * product_name : Discover 125
             * mrp : 48000
             * srp : 45000
             */

            private String period;
            private String product_name;
            private String mrp;
            private String srp;

            public String getPeriod() {
                return period;
            }

            public void setPeriod(String period) {
                this.period = period;
            }

            public String getProduct_name() {
                return product_name;
            }

            public void setProduct_name(String product_name) {
                this.product_name = product_name;
            }

            public String getMrp() {
                return mrp;
            }

            public void setMrp(String mrp) {
                this.mrp = mrp;
            }

            public String getSrp() {
                return srp;
            }

            public void setSrp(String srp) {
                this.srp = srp;
            }
        }
    }
}
