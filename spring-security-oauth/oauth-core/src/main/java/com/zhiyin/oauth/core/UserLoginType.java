package com.zhiyin.oauth.core;

import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by hg on 2017/2/11.
 */
@Slf4j
public enum UserLoginType {

    Default() {
        @Override
        public UserFindType findType() {
            return UserFindType.FindByTelephone;
        }

        @Override
        public String namePrefix() {
            return "";
        }

    },

    Telephone() {
        @Override
        public UserFindType findType() {
            return UserFindType.FindByTelephone;
        }

        @Override
        public String namePrefix() {
            return "TEL$";
        }

    },
    Third() {
        @Override
        public UserFindType findType() {
            return UserFindType.FindById;
        }

        @Override
        public String namePrefix() {
            return "THIRD$";
        }

    };
//    Wixin() {
//        @Override
//        public String oauthName(String loginName) {
//            return namePrefix() + loginName;
//        }
//        @Override
//        public String findVal(String oauthName) {
//            return oauthName.substring( namePrefix().length() );
//        }
//
//        @Override
//        public UserFindType findType() {
//            return UserFindType.FindById;
//        }
//
//        @Override
//        public String namePrefix() {
//            return "Weixin$";
//        }
//
//    };


    public String oauthName(String loginName) {
        return namePrefix() + loginName;
    }

    public String findVal(String oauthName) {
        return oauthName.substring( namePrefix().length() );
    }

    public abstract UserFindType findType();

    public abstract String namePrefix();


    public static UserLoginType loginType(String oauthUserName){
        if(Strings.isNullOrEmpty(oauthUserName)){
            return UserLoginType.Default;
        }
        for (UserLoginType userLoginType : UserLoginType.values()) {
            if( oauthUserName.startsWith( userLoginType.namePrefix()) ){
                return userLoginType;
            }
        }
        return UserLoginType.Default;
    }
}
