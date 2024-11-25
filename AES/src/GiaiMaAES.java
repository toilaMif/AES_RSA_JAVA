import java.util.Base64;

public class GiaiMaAES {

	// InvS-box
	String[][] InvSbox = {
			{ "52", "09", "6A", "D5", "30", "36", "A5", "38", "BF", "40", "A3", "9E", "81", "F3", "D7", "FB" },
			{ "7C", "E3", "39", "82", "9B", "2F", "FF", "87", "34", "8E", "43", "44", "C4", "DE", "E9", "CB" },
			{ "54", "7B", "94", "32", "A6", "C2", "23", "3D", "EE", "4C", "95", "0B", "42", "FA", "C3", "4E" },
			{ "08", "2E", "A1", "66", "28", "D9", "24", "B2", "76", "5B", "A2", "49", "6D", "8B", "D1", "25" },
			{ "72", "F8", "F6", "64", "86", "68", "98", "16", "D4", "A4", "5C", "CC", "5D", "65", "B6", "92" },
			{ "6C", "70", "48", "50", "FD", "ED", "B9", "DA", "5E", "15", "46", "57", "A7", "8D", "9D", "84" },
			{ "90", "D8", "AB", "00", "8C", "BC", "D3", "0A", "F7", "E4", "58", "05", "B8", "B3", "45", "06" },
			{ "D0", "2C", "1E", "8F", "CA", "3F", "0F", "02", "C1", "AF", "BD", "03", "01", "13", "8A", "6B" },
			{ "3A", "91", "11", "41", "4F", "67", "DC", "EA", "97", "F2", "CF", "CE", "F0", "B4", "E6", "73" },
			{ "96", "AC", "74", "22", "E7", "AD", "35", "85", "E2", "F9", "37", "E8", "1C", "75", "DF", "6E" },
			{ "47", "F1", "1A", "71", "1D", "29", "C5", "89", "6F", "B7", "62", "0E", "AA", "18", "BE", "1B" },
			{ "FC", "56", "3E", "4B", "C6", "D2", "79", "20", "9A", "DB", "C0", "FE", "78", "CD", "5A", "F4" },
			{ "1F", "DD", "A8", "33", "88", "07", "C7", "31", "B1", "12", "10", "59", "27", "80", "EC", "5F" },
			{ "60", "51", "7F", "A9", "19", "B5", "4A", "0D", "2D", "E5", "7A", "9F", "93", "C9", "9C", "EF" },
			{ "A0", "E0", "3B", "4D", "AE", "2A", "F5", "B0", "C8", "EB", "BB", "3C", "83", "53", "99", "61" },
			{ "17", "2B", "04", "7E", "BA", "77", "D6", "26", "E1", "69", "14", "63", "55", "21", "0C", "7D" } };

	int[][] invMixMatrix = { { 0x0E, 0x0B, 0x0D, 0x09 }, { 0x09, 0x0E, 0x0B, 0x0D }, { 0x0D, 0x09, 0x0E, 0x0B },
			{ 0x0B, 0x0D, 0x09, 0x0E } };

	// Hàm chuyển đổi hex sang decimal
	private int hex_dec(String hex) {
		return Integer.parseInt(hex, 16);
	}

	// InvSubBytes
	public String[][] InvSubBytes(String[][] state, String[][] invSbox) {

		String[][] state_temp = new String[state.length][state[0].length];
		String regex = "(?<=\\G.{1})";
		for (int i = 0; i < state.length; i++) {
			for (int j = 0; j < state[0].length; j++) {
				String[] hexStrings = state[i][j].split(regex);
				if (hexStrings.length == 2) {
					state_temp[i][j] = invSbox[hex_dec(hexStrings[0])][hex_dec(hexStrings[1])];
				}
			}
		}

		return state_temp;
	}

	// InvAddRoundKey
	public String[][] InvAddRoundKey(String[][] state, String[][] RoundKey) {
		String[][] state_temp = new String[state.length][state[0].length];
		String regex = "(?<=\\G.{2})";

		for (int i = 0; i < state.length; i++) {
			for (int j = 0; j < state[0].length; j++) {
				String[] hexStrings = state[i][j].split(regex);
				String[] hexStringr = RoundKey[i][j].split(regex);
				if (hexStrings.length == 1 && hexStringr.length == 1) {
					int temp = hex_dec(hexStrings[0]) ^ hex_dec(hexStringr[0]);
					state_temp[i][j] = String.format("%02X", temp);
				}
			}
		}
		return state_temp;
	}

	// InvMixColumns
	public static String[][] InvMixColumns(String[][] state, int[][] invMixMatrix) {
		String[][] state_temp = new String[state.length][state[0].length];

		for (int i = 0; i < state_temp.length; i++) {
			for (int j = 0; j < state_temp[0].length; j++) {
				int tempXOR = 0;
				for (int a = 0; a < state_temp.length; a++) {
					int value = Integer.parseInt(state[a][j], 16);
					tempXOR ^= galoisMultiply(value, invMixMatrix[i][a]);
				}
				state_temp[i][j] = String.format("%02X", tempXOR);
			}
		}

		return state_temp;
	}

	public static int galoisMultiply(int a, int b) {
		int p = 0;
		for (int counter = 0; counter < 8; counter++) {
			if ((b & 1) != 0) {
				p ^= a;
			}
			boolean carry = (a & 0x80) != 0;
			a <<= 1;
			if (carry) {
				a ^= 0x1b;
			}
			b >>= 1;
		}
		return p & 0xFF;
	}

	// InvShiftRows
	public String[][] InvShiftRows(String[][] state) {
		String[][] state_temp = new String[state.length][state[0].length];

		for (int i = 0; i < state_temp.length; i++) {
			for (int j = 0; j < state_temp[0].length; j++) {
				state_temp[i][j] = state[i][(j - i + state_temp[0].length) % state_temp[0].length];
			}
		}
		return state_temp;
	}

	public static byte[][] chiaKhoi(byte[] input) {
		
		int numBlocks = (input.length + 16 - 1) / 16; 
		byte[][] blocks = new byte[numBlocks][16];

		for (int i = 0; i < numBlocks; i++) {
			for (int j = 0; j < 16; j++) {
				int index = i * 16 + j;
				blocks[i][j] = (index < input.length) ? input[index] : 0; 
			}
		}
		return blocks;
	}

	// Hàm chuyển chuỗi hex thành mảng byte
	public static byte[] hexStringToByteArray(String hex) {
		int len = hex.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			data[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4) + Character.digit(hex.charAt(i + 1), 16));
		}
		return data;
	}

	public String GIAIMA(String cipherText, String key) {
		GiaiMaAES gmAES = new GiaiMaAES();
		StringBuilder chuoiGiaiMa = new StringBuilder();

		byte[] byteArray = hexStringToByteArray(cipherText);

		byte[][] blocks = chiaKhoi(byteArray);

		for (byte[] block : blocks) {
			String[][] state = textToMatrix(block);

			byte[] initialKey = KeyExpansion.adjustKey(key.getBytes());

			byte[][] roundKeys = KeyExpansion.keyExpansion(initialKey);

			String[][] roundKey = textToMatrix(roundKeys[10]);

			// Vòng giải mã AES
			state = gmAES.InvAddRoundKey(state, roundKey); 

			for (int i = 9; i >= 1; i--) {
				state = gmAES.InvShiftRows(state); 

				state = gmAES.InvSubBytes(state, gmAES.InvSbox); 

				state = gmAES.InvAddRoundKey(state, textToMatrix(roundKeys[i]));

				state = gmAES.InvMixColumns(state, gmAES.invMixMatrix); 
			}

			state = gmAES.InvShiftRows(state);
			state = gmAES.InvSubBytes(state, gmAES.InvSbox);

			state = gmAES.InvAddRoundKey(state, textToMatrix(roundKeys[0]));
			System.out.println("Ma trận plaintext đầu ra:");
			printMatrix(state);

			// Chuyển ma trận về chuỗi và thêm vào kết quả
			chuoiGiaiMa.append(matrixToText(state));
		}

		return chuoiGiaiMa.toString();
	}

	// Phương thức in ma trận
	private void printMatrix(String[][] matrix) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}

	// Chuyển đổi từ byte[] thành ma trận 4x4
	private String[][] textToMatrix(byte[] block) {
		String[][] matrix = new String[4][4];
		for (int i = 0; i < block.length; i++) {
			int row = i / 4;
			int col = i % 4;
			matrix[row][col] = String.format("%02X", block[i] & 0xFF);
		}
		return matrix;
	}

	// Chuyển đổi ma trận 4x4 thành chuỗi hex
	private String matrixToText(String[][] matrix) {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				result.append(matrix[i][j]);
			}
		}
		return result.toString();
	}

}
