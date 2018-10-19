import java.text.DecimalFormat;

/**
 * ����̨���������
 * ע�⣺��eclipse�������޷���տ���̨����һֱ����
 * ������ʾ����ȷ����������������������л�������ʾ
 * ��IDEA������Ҳ��������ʾ����
 * @author YX
 *
 */
public class ConsoleProgressBar {

    private long minimum = 0; // ��������ʼֵ

    private long maximum = 100; // ���������ֵ

    private long barLen = 100; // ����������

    private char showChar = '='; // ���ڽ�������ʾ���ַ�

    private DecimalFormat formater = new DecimalFormat("#.##%");

    /**
     * ʹ��ϵͳ��׼�������ʾ�ַ�����������ٷֱȡ�
     */
    public ConsoleProgressBar() {
    }

    /**
     * ʹ��ϵͳ��׼�������ʾ�ַ�����������ٷֱȡ�
     *
     * @param minimum ��������ʼֵ
     * @param maximum ���������ֵ
     * @param barLen ����������
     */
    public ConsoleProgressBar(long minimum, long maximum,
                              long barLen) {
        this(minimum, maximum, barLen, '=');
    }

    /**
     * ʹ��ϵͳ��׼�������ʾ�ַ�����������ٷֱȡ�
     *
     * @param minimum ��������ʼֵ
     * @param maximum ���������ֵ
     * @param barLen ����������
     * @param showChar ���ڽ�������ʾ���ַ�
     */
    public ConsoleProgressBar(long minimum, long maximum,
                              long barLen, char showChar) {
        this.minimum = minimum;
        this.maximum = maximum;
        this.barLen = barLen;
        this.showChar = showChar;
    }

    /**
     * ��ʾ��������
     *
     * @param value ��ǰ���ȡ����ȱ�����ڻ������ʼ����С�ڵ��ڽ����㣨start <= current <= end����
     */
    public void show(long value) {
        if (value < minimum || value > maximum) {
            return;
        }

        reset();
        minimum = value;
        float rate = (float) (minimum*1.0 / maximum);
        long len = (long) (rate * barLen);
        draw(len, rate);
        if (minimum == maximum) {
            afterComplete();
        }
    }

    private void draw(long len, float rate) {
        System.out.print("Progress: ");
        //ѭ����ӡ��ʶ��
        for (int i = 0; i < len; i++) {
            System.out.print(showChar);
        }
        //ѭ����ӡʣ��ո�λ��
        for(int i=0;i<barLen-len;i++){
            System.out.print(" ");
        }

        System.out.print(' ');
        System.out.print(format(rate));
    }


    private void reset() {
        System.out.print('\r'); //����ƶ�������
    }

    private void afterComplete() {
        System.out.print('\n');
    }

    private String format(float num) {
        return formater.format(num);
    }

    public static void main(String[] args) throws InterruptedException {
        ConsoleProgressBar cpb = new ConsoleProgressBar(0, 100, 50, '#');
        for (int i = 1; i <= 100; i++) {
            cpb.show(i);
            Thread.sleep(100);
        }
    }

}