package in.fusionbit.shreejeepricelist.apimodels;

import java.util.Date;
import java.util.List;

public class UserModel {


    /**
     * user : {"admin_name":"jeet","admin_id":"12","admin_rights":["6","199","7"],"admin_logged_in":true,"session_id":"dd7daeade81052531f18c7d27974101c"}
     * user_list : [{"admin_id":"69","admin_email":"test@gmail.com","admin_name":"test","admin_username":"test","admin_password":"2aSOXQ0Gkx5NA","last_login":"2018-05-24 17:14:55","date_added":"2018-03-20 14:26:55","date_modified":"2018-03-20 14:26:55","admin_hash":"2a10c6f518483f52a2183056a1","is_active":"1","type":"0"}]
     */

    private UserBean user;
    private List<UserListBean> user_list;

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public List<UserListBean> getUser_list() {
        return user_list;
    }

    public void setUser_list(List<UserListBean> user_list) {
        this.user_list = user_list;
    }

    public static class UserBean {
        /**
         * admin_name : jeet
         * admin_id : 12
         * admin_rights : ["6","199","7"]
         * admin_logged_in : true
         * session_id : dd7daeade81052531f18c7d27974101c
         */

        private String admin_name;
        private String admin_id;
        private boolean admin_logged_in;
        private String session_id;
        private List<String> admin_rights;

        public String getAdmin_name() {
            return admin_name;
        }

        public void setAdmin_name(String admin_name) {
            this.admin_name = admin_name;
        }

        public String getAdmin_id() {
            return admin_id;
        }

        public void setAdmin_id(String admin_id) {
            this.admin_id = admin_id;
        }

        public boolean isAdmin_logged_in() {
            return admin_logged_in;
        }

        public void setAdmin_logged_in(boolean admin_logged_in) {
            this.admin_logged_in = admin_logged_in;
        }

        public String getSession_id() {
            return session_id;
        }

        public void setSession_id(String session_id) {
            this.session_id = session_id;
        }

        public List<String> getAdmin_rights() {
            return admin_rights;
        }

        public void setAdmin_rights(List<String> admin_rights) {
            this.admin_rights = admin_rights;
        }
    }

    public static class UserListBean {
        /**
         * admin_id : 69
         * admin_email : test@gmail.com
         * admin_name : test
         * admin_username : test
         * admin_password : 2aSOXQ0Gkx5NA
         * last_login : 2018-05-24 17:14:55
         * date_added : 2018-03-20 14:26:55
         * date_modified : 2018-03-20 14:26:55
         * admin_hash : 2a10c6f518483f52a2183056a1
         * is_active : 1
         * type : 0
         */

        private String admin_id;
        private String admin_email;
        private String admin_name;
        private String admin_username;
        private String admin_password;
        private Date last_login;
        private Date date_added;
        private Date date_modified;
        private String admin_hash;
        private String is_active;
        private String type;

        public String getAdmin_id() {
            return admin_id;
        }

        public void setAdmin_id(String admin_id) {
            this.admin_id = admin_id;
        }

        public String getAdmin_email() {
            return admin_email;
        }

        public void setAdmin_email(String admin_email) {
            this.admin_email = admin_email;
        }

        public String getAdmin_name() {
            return admin_name;
        }

        public void setAdmin_name(String admin_name) {
            this.admin_name = admin_name;
        }

        public String getAdmin_username() {
            return admin_username;
        }

        public void setAdmin_username(String admin_username) {
            this.admin_username = admin_username;
        }

        public String getAdmin_password() {
            return admin_password;
        }

        public void setAdmin_password(String admin_password) {
            this.admin_password = admin_password;
        }

        public Date getLast_login() {
            return last_login;
        }

        public Date getDate_added() {
            return date_added;
        }

        public Date getDate_modified() {
            return date_modified;
        }

        public String getAdmin_hash() {
            return admin_hash;
        }

        public void setAdmin_hash(String admin_hash) {
            this.admin_hash = admin_hash;
        }

        public String getIs_active() {
            return is_active;
        }

        public void setIs_active(String is_active) {
            this.is_active = is_active;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
