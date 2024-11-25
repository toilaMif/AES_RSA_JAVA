import java.util.Scanner;

public class KeyExpansion {

    private static final int Nb = 4; // Số cột của ma trận 
    private static final int Nk = 4; // Số từ trong khóa đầu vào 
    private static final int Nr = 10; // Số vòng

    private static final int[] Rcon = { 
        0x01, 0x02, 0x04, 0x08, 0x10, 0x20, 0x40, 0x80, 0x1B, 0x36 
    };

    
    // Điều chỉnh độ dài khóa
    public static byte[] adjustKey(byte[] key) {
        byte[] adjustedKey = new byte[16];
        System.arraycopy(key, 0, adjustedKey, 0, Math.min(key.length, 16));
        return adjustedKey;
    }

    // Hàm mở rộng khóa
    public static byte[][] keyExpansion(byte[] key) {
        int keyColumns = Nk * 4; // số byte khóa ban đầu
        int expandedColumns = Nb * (Nr + 1) * 4; //số byte cho khóa mỡr rộng
        byte[] expandedKey = new byte[expandedColumns];

        System.arraycopy(key, 0, expandedKey, 0, keyColumns);

        for (int i = Nk; i < Nb * (Nr + 1); i++) {
            byte[] temp = new byte[4];
            System.arraycopy(expandedKey, (i - 1) * 4, temp, 0, 4);

            if (i % Nk == 0) {
                temp = xorWords(subWord(rotWord(temp)), rconWord(i / Nk));
            }

            for (int j = 0; j < 4; j++) {
                expandedKey[i * 4 + j] = (byte) (expandedKey[(i - Nk) * 4 + j] ^ temp[j]);
            }
        }

        // Chuyển mảng 1 chiều thành mảng 2 chiều (khóa vòng)
        byte[][] roundKeys = new byte[Nr + 1][Nb * 4];
        for (int i = 0; i < Nr + 1; i++) {
            System.arraycopy(expandedKey, i * Nb * 4, roundKeys[i], 0, Nb * 4);
        }

        return roundKeys;
    }

    // Hàm xoay vòng (RotWord)
    private static byte[] rotWord(byte[] word) {
        return new byte[] { word[1], word[2], word[3], word[0] };
    }

    // Hàm thay thế byte (SubWord) sử dụng S-box
    private static byte[] subWord(byte[] word) {
        String[][] sBox = {
            { "63", "7C", "77", "7B", "F2", "6B", "6F", "C5", "30", "01", "67", "2B", "FE", "D7", "AB", "76" },
            { "CA", "82", "C9", "7D", "FA", "59", "47", "F0", "AD", "D4", "A2", "AF", "9C", "A4", "72", "C0" },
            { "B7", "FD", "93", "26", "36", "3F", "F7", "CC", "34", "A5", "E5", "F1", "71", "D8", "31", "15" },
            { "04", "C7", "23", "C3", "18", "96", "05", "9A", "07", "12", "80", "E2", "EB", "27", "B2", "75" },
            { "09", "83", "2C", "1A", "1B", "6E", "5A", "A0", "52", "3B", "D6", "B3", "29", "E3", "2F", "84" },
            { "53", "D1", "00", "ED", "20", "FC", "B1", "5B", "6A", "CB", "BE", "39", "4A", "4C", "58", "CF" },
            { "D0", "EF", "AA", "FB", "43", "4D", "33", "85", "45", "F9", "02", "7F", "50", "3C", "9F", "A8" },
            { "51", "A3", "40", "8F", "92", "9D", "38", "F5", "BC", "B6", "DA", "21", "10", "FF", "F3", "D2" },
            { "CD", "0C", "13", "EC", "5F", "97", "44", "17", "C4", "A7", "7E", "3D", "64", "5D", "19", "73" },
            { "60", "81", "4F", "DC", "22", "2A", "90", "88", "46", "EE", "B8", "14", "DE", "5E", "0B", "DB" },
            { "E0", "32", "3A", "0A", "49", "06", "24", "5C", "C2", "D3", "AC", "62", "91", "95", "E4", "79" },
            { "E7", "C8", "37", "6D", "8D", "D5", "4E", "A9", "6C", "56", "F4", "EA", "65", "7A", "AE", "08" },
            { "BA", "78", "25", "2E", "1C", "A6", "B4", "C6", "E8", "DD", "74", "1F", "4B", "BD", "8B", "8A" },
            { "70", "3E", "B5", "66", "48", "03", "F6", "0E", "61", "35", "57", "B9", "86", "C1", "1D", "9E" },
            { "E1", "F8", "98", "11", "69", "D9", "8E", "94", "9B", "1E", "87", "E9", "CE", "55", "28", "DF" },
            { "8C", "A1", "89", "0D", "BF", "E6", "42", "68", "41", "99", "2D", "0F", "B0", "54", "BB", "16" }
        };

        byte[] result = new byte[4];
        for (int i = 0; i < word.length; i++) {
            int row = (word[i] >> 4) & 0x0F; // Lấy 4 bit cao
            int col = word[i] & 0x0F;        // Lấy 4 bit thấp
            result[i] = (byte) Integer.parseInt(sBox[row][col], 16); // Chuyển đổi hex -> byte
        }
        return result;
    }

    // Hàm XOR với Rcon
    private static byte[] rconWord(int round) {
        return new byte[] { (byte) Rcon[round - 1], 0x00, 0x00, 0x00 };
    }

    // Hàm XOR hai từ
    private static byte[] xorWords(byte[] word1, byte[] word2) {
        byte[] result = new byte[4];
        for (int i = 0; i < 4; i++) {
            result[i] = (byte) (word1[i] ^ word2[i]);
        }
        return result;
    }


}
