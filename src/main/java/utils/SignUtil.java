package utils;

import java.util.Date;

/**
 * 签到工具类，包括时间对比
 *
 * @author Fish
 * created by 2018-05-17 16:24
 */
public class SignUtil
{
    /**
     * 这是一段时间，包括区间开始和结束
     */
    public static final class SignTime // 签到专用时间类
    {
        private int hour;
        private int minute;

        public SignTime(int hour, int minute)
        {
            this.hour = hour;
            this.minute = minute;
        }
    }

    // 早上开课时间
    private static final SignTime MORNING_BEGIN_TIME = new SignTime(9, 30);

    // 早上结课时间
    private static final SignTime MORNING_END_TIME = new SignTime(12, 0);

    // 下午开课时间
    private static final SignTime AFTERNOON_BEGIN_TIME = new SignTime(9, 30);

    // 下午结课时间
    private static final SignTime AFTERNOON_END_TIME = new SignTime(12, 0);

    // 晚上开课时间
    private static final SignTime EVENING_BEGIN_TIME = new SignTime(9, 30);

    // 晚上结课时间
    private static final SignTime EVENING_END_TIME = new SignTime(12, 0);
}
