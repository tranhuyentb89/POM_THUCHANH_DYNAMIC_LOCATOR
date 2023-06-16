package commons;

public class AbstractPageUI {
	public static final String DYNAMIC_LINK_PAGE = "//a[(text()='%s')]";
	public static final String DYNAMIC_BLANK_ERR_MESSAGE_NEW_CUSTOMER ="//label[text()='%s']";
	public static final String DYNAMIC_TEXTBOX_AND_SUBMIT_BUTTON ="//input[@name='%s']";
	public static final String DYNAMIC_REGISTER_CUSTOMER_SUCCESS ="//td[text()='%s']//following-sibling::td";
	public static final String DYNAMIC_TITLE_FORM = "//p[contains(text(),'%s')]";
}
