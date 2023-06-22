package com.qa.opencart.utils;

import java.util.Arrays;
import java.util.List;

public class AppConstants {

	public static final String LOGIN_PAGE_TITLE= "Account Login";
	public static final String ACCOUNT_PAGE_TITLE= "My Account";

	public static final String LOGIN_PAGE_URL_FRACTION_VALUE= "account/login";
	public static final String ACCOUNT_PAGE_URL_FRACTION_VALUE="account/account";

	public static final int SHORT_DEFAULT_WAIT= 5;
	public static final int MEDIUM_DEFAULT_WAIT= 10;
	public static final int LONG_DEFAULT_WAIT= 15;
	public static final int DOUBLE_LONG_DEFAULT_WAIT= 30;

	public static final List<String> EXP_ACCOUNT_HEADERS_LIST= Arrays.asList("My Account", "My Orders", "My Affiliate Account", "Newsletter");
	public static final Object LOGIN_ERROR_MSG = "Warning: No match for E-Mail Address and/or Password.";
	public static final String REG_SHEET_NAME = "register";
	public static final String LOGIN_SHEET_NAME = "login";


}
