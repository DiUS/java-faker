package com.github.javafaker.idnumbers;

import com.github.javafaker.Faker;

/**
 * @author HanXiao 11710906
 **/
public class CnIdNumber {
    private static final int[] coefficients = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
    private static final String[] checkCode = {"1", "0", "X",
            "9", "8", "7", "6", "5", "4", "3", "2"};

    private static final String[] invalidCNPatterns = {
            "\\d{6}-1[0-8]\\d{6}-\\d{3}", //必须是19xx年
            "\\d{6}-2[1-9]\\d{6}-\\d{3}", //必须是20xx年
            "\\d{6}-19[0-4]\\d{5}-\\d{3}", //必须是1950年后
            "\\d{6}-20[2-9]\\d{5}-\\d{3}",

            "\\d{6}-\\d{4}00\\d{2}-\\d{3}", //月份0开头时，不会有00
            "\\d{6}-\\d{4}1[3-9]\\d{2}-\\d{3}", //月份1开头时，只有10，11，12【0-2】
    };

    /**
     * Generating the 18th digit, check code, according to the first 17 digits.
     * @param str
     * @return
     */

    public String getCnEncoding(String str) {
        String tem = str;
        char[] id = tem.replaceAll("-", "").toCharArray();
        int code = 0;
        for (int i = 0; i < id.length; i++) {
            code += ((int) id[i] - (int) '0') * coefficients[i];
        }
        String finalCheckCode = checkCode[code% 11];
        return finalCheckCode;
    }

    /**
     * Generate the valid first 17 bits idNumber.
     * @param f
     * @return
     */
    public String getMainString(Faker f) {
        String cn = f.regexify(
                "[1-6][1-3][0-2][0-9]21-[1-2]\\d{3}[0-1][0-9][0-2][0-8]-\\d{3}");
        //原理：通过正则表达式生成符合条件的 id，并且排除掉不符合的id。生成不符合的则重新生成。
        //正则表达式中，\d 匹配一个数字字符。等价于 [0-9]。
        boolean isValid = true;
        for (String invalidSSNPattern : invalidCNPatterns) {
            if (cn.matches(invalidSSNPattern)) {
                isValid = false;
                break;
            }
        }
        if (!isValid) {
            cn = getMainString(f);
        }
        return cn;
    }
    /**
     * Combine the first 17 digits and the last 1 digit check code to generate a valid cn idNumber.
     * @param f
     * @return
     */
    public String getValidCn(Faker f) {
        String mainstr = getMainString(f);
        String cheakcode = getCnEncoding(mainstr);
        return mainstr.concat(cheakcode);
    }
}
/*
410321
412281
中国身份证号前6位：地区号
地区号前2位:【11，15】，【21，23】，【31，37】，【41，46】，
           【50，54】，【61，65】，【81，82】

11：北京，100-113，200，221-229
12：天津，100-113，200，221-225
13：河北，100-107，121-133，181-185，200-206，221-230，281-283，301-304，321-324
         400-406，421-435，481，500-503，521-535，581-582，600-604，621-638
         681-684，700-706，721-733，800-804，821-828，900-903，921-930，981-984
         1000-1002，1022-1028，1081-1082，1100-1102，1121-1128，1181-1182
14：山西，到2000以上了，且山西没有1000的，0624过了直接2200（2200-2733）
15：内蒙，0430过了直接2100，到2923结束

21：辽宁，100-106，111-114，122-124，181，200（规律是0，11，24【21/22/23/24都可能】，81）
         1481结束
22：吉林，同辽宁，2426结束
23：黑龙江，没有11分级，为0.21.81，2723结束

31：上海，100-117，200，225-226，229-300
32：江苏，0.11.21.81，1324结束
33：浙江，2529
34：安徽，2923
35：福建，2231
36：江西，2532
37：山东，2930

41：河南 2829
42：湖北 2828跳9001，9004，9005，9006，9021
43：湖南 3130
44：广东 5381（2001跳5100）
45：广西 0987跳2100，2731
46：海南 0201

50：重庆 0384
51：四川 1681跳3000，3922
52：贵州 2732
53：云南 0428跳2100，3528
54：西藏 0127跳2100，2627

61：陕西 2732，0踢跳2
62：甘肃 3027，0跳2
63：青海 2823，0跳2
64：宁夏 2226，0跳2
65：新疆 0跳2， 4326跳9000，9001

8182为港澳
 */