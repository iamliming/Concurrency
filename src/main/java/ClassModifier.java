/**
 * <一句话功能简述> <功能详细描述>
 *
 * @author liming
 * @version [版本号, 二月 16, 2016]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ClassModifier
{
    private static final int POOL_COUNT_IDX = 8;
    private static final int CONSTANT_UTF8_INFO = 1;
    private static final int[] CONSTANT_ITEM_LENGTH = { -1, -1, -1, 5, 5, 9,9 ,3, 3, 5, 5, 5, 5};
    private static final int u1 = 1;
    private static final int u2 = 2;
    private byte[] classByte;

    public ClassModifier(byte[] classByte)
    {
        this.classByte = classByte;
    }

    public byte[] modifyUTF8Constant(String oldStr, String newStr)
    {
        int cpc = getConstantPoolCount();
        int offset = POOL_COUNT_IDX + u2;
        for (int i = 0; i < cpc; i++)
        {
            int tag = ByteUtils.byte2Int(classByte, offset, u1);
            if(tag == CONSTANT_UTF8_INFO)
            {
                int len = ByteUtils.byte2Int(classByte, offset + u1, u2);
                offset += (u1 + u2);
                String str = ByteUtils.byte2String(classByte, offset, len);
                if(str.equalsIgnoreCase(oldStr))
                {
                    byte[] strBytes = ByteUtils.string2Bytes(newStr);
                    byte[] strLen = ByteUtils.int2Byte(newStr.length(), u2);
                    classByte = ByteUtils.bytesReplace(classByte, offset-u2, u2, strLen);
                    classByte = ByteUtils.bytesReplace(classByte, offset, len, strBytes);
                    return classByte;
                }
                else
                {
                    offset += len;
                }
            }
            else
            {
                offset += CONSTANT_ITEM_LENGTH[tag];
            }
        }
        return classByte;
    }

    public int getConstantPoolCount()
    {
        return ByteUtils.byte2Int(classByte, POOL_COUNT_IDX, u2);
    }
}
