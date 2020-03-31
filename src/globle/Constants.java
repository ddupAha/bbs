package globle;

import java.awt.*;

public class Constants {

    // 验证码的字体库
    public static Font[] codeFont = { new Font("Times New Roman", Font.PLAIN, 30),
            new Font("Times New Roman", Font.PLAIN, 30), new Font("Times New Roman", Font.PLAIN, 30),
            new Font("Times New Roman", Font.PLAIN, 30) };

    // 验证码数字颜色库
    public static Color[] color = { Color.BLACK, Color.RED, Color.DARK_GRAY, Color.BLUE };

    // 验证码的字符库
    public static final String IMAGE_CHAR = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    // 验证码的宽度
    public static final Integer IMAGE_WIDTH = 120;

    // 验证码的高度
    public static final Integer IMAGE_HEIGHT = 40;

    /**
     * 登陆错误提示信息的键名
     */
    public static String GLOBAL_ERROR_KEY = "error_msg";
}
