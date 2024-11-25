import java.util.Arrays;
import java.util.Iterator;

public class MaHoaAES {

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
			{ "8C", "A1", "89", "0D", "BF", "E6", "42", "68", "41", "99", "2D", "0F", "B0", "54", "BB", "16" } };

			
	
//	String[][] state = { { "63", "47", "A2", "F0" }, { "FC", "A1", "5B", "6A" }, { "FC", "B1", "6B", "6A" },
//			{ "FC", "B1", "5B", "AA" }
//
//	};
//	String[][] roundKey = { { "2B", "28", "AB", "09" }, { "2B", "28", "AB", "09" }, { "2B", "28", "AB", "09" },
//			{ "2B", "28", "1B", "09" }

//	};
	 int[][] mixMatrix = {
	            {2, 3, 1, 1},
	            {1, 2, 3, 1},
	            {1, 1, 2, 3},
	            {3, 1, 1, 2}
	        };

	private int hex_dec(String hex) {
		return Integer.parseInt(hex, 16);
	}

//	SubBytes
	public String[][] SubBytes(String[][] state, String[][] Sbox) {

		String[][] state_temp = new String[state.length][state[0].length];
		String regex = "(?<=\\G.{1})";
		for (int i = 0; i < state.length; i++) {
			for (int j = 0; j < state[0].length; j++) {
				String[] hexStrings = state[i][j].split(regex);
				if (hexStrings.length == 2) {
					state_temp[i][j] = Sbox[hex_dec(hexStrings[0])][hex_dec(hexStrings[1])];
				}
			}
		}

		return state_temp;
	}
	

//	Add Round Key
	public String[][] AddRoundKey(String[][] state, String[][] RoundKey) {
		String[][] state_temp = new String[state.length][state[0].length];
		String regex = "(?<=\\G.{2})";

		for (int i = 0; i < state.length; i++) {
			for (int j = 0; j < state[0].length; j++) {
				String[] hexStrings = state[i][j].split(regex);
				String[] hexStringr = RoundKey[i][j].split(regex);
				if (hexStrings.length == 1 && hexStringr.length == 1) {
					int temp = hex_dec(hexStrings[0]) ^  hex_dec(hexStringr[0]);
//					state_temp[i][j] =  Integer.toHexString(temp);
					state_temp[i][j] =  String.format("%02X", temp);
					
				}
			}
		}
		
		return state_temp;
	}
	


//	MixColumns
	public static String[][] MixColumns(String[][] state, int[][] mixMatrix) {
        String[][] state_temp = new String[state.length][state[0].length];

        for (int i = 0; i < state_temp.length; i++) {
            for (int j = 0; j < state_temp[0].length; j++) {
                int tempXOR = 0;
                for (int a = 0; a < state_temp.length; a++) {
                    int value = Integer.parseInt(state[a][j], 16);
                    tempXOR ^= galoisMultiply(value, mixMatrix[i][a]);
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

	
//	ShiftRows
	public String[][] ShiftRows(String[][] state) {
		String[][] state_temp = new String[state.length][state[0].length];
		
		for (int i = 0; i < state_temp.length; i++) {
			for (int j = 0; j < state_temp[0].length; j++) {
				state_temp[i][j] =  state[i][(j + i) % state_temp[0].length];
			}
		}
		return state_temp;
	}
//	public static void main(String[] args) {
//		MaHoaAES mhAes = new MaHoaAES();
//		String[][] state_temp = mhAes.SubBytes(mhAes.state, mhAes.sBox);
//		String[][] state_temp1 = mhAes.AddRoundKey(mhAes.state, mhAes.roundKey); 
//		String[][] state_temp2 = mhAes.MixColumns(mhAes.state, mhAes.mixMatrix); 
//		String[][] state_temp3 = mhAes.ShiftRows(mhAes.state); 
//		
//		System.out.println("Subyte");
//		for (int a = 0; a < state_temp.length; a++) {
//			for (int b = 0; b < state_temp[0].length; b++) {
//				System.out.print(state_temp[a][b]+"|");
//				
//			}
//			System.out.println();
//		}
//		
//		System.out.println("AddRoundKey");
//		for (int i = 0; i < state_temp1.length; i++) {
//			for (int j = 0; j < state_temp1[0].length; j++) {
//				System.out.print(state_temp1[i][j]+"|");
//				
//			}
//			System.out.println();
//		}
//		
//		System.out.println("MixColumns");
//		for (int i = 0; i < state_temp2.length; i++) {
//			for (int j = 0; j < state_temp2[0].length; j++) {
//				System.out.print(state_temp2[i][j]+"|");
//				
//			}
//			System.out.println();
//		}
//		
//		System.out.println("ShiftRows");
//		for (int i = 0; i < state_temp3.length; i++) {
//			for (int j = 0; j < state_temp3[0].length; j++) {
//				System.out.print(state_temp3[i][j]+"|");
//				
//			}
//			System.out.println();
//		}
//		
//		
//		
//	}
	public static byte[][] chiaKhoi(String input) {
	    byte[] data = input.getBytes();
	    
	    int numBlocks = (data.length + 16 - 1) / 16; 
	    byte[][] blocks = new byte[numBlocks][16];

	    for (int i = 0; i < numBlocks; i++) {
	        for (int j = 0; j < 16; j++) {
	            int index = i * 16 + j;
	            blocks[i][j] = (index < data.length) ? data[index] : 0; 
	        }
	    }
	    return blocks;
	}

	

	// Phương thức mã hóa
	public String MAHOA(String plainText, String key) {
	    MaHoaAES mhAes = new MaHoaAES();
	    StringBuilder chuoiMaHoa = new StringBuilder();
	    byte[][] blocks = chiaKhoi(plainText);

	    for (byte[] block : blocks) {
	        // Chuyển block thành ma trận 4x4
	        String[][] state = textToMatrix(block);
	        System.out.println("Ma trận plaintext đầu vào:");
	        printMatrix(state);

	       
	        byte[] initialKey = KeyExpansion.adjustKey(key.getBytes());

	        
	        byte[][] roundKeys = KeyExpansion.keyExpansion(initialKey);

	        
	        String[][] roundKey = textToMatrix(roundKeys[0]);


	        // Vòng mã hóa AES
	        state = mhAes.AddRoundKey(state, roundKey); 

	        for (int i = 1; i <= 9; i++) {
	            state = mhAes.SubBytes(state, mhAes.sBox);

	            state = mhAes.ShiftRows(state);

	            state = mhAes.MixColumns(state, mhAes.mixMatrix); 

	            state = mhAes.AddRoundKey(state, textToMatrix(roundKeys[i]));
	        }

	        state = mhAes.SubBytes(state, mhAes.sBox);

	        state = mhAes.ShiftRows(state);

	        state = mhAes.AddRoundKey(state, textToMatrix(roundKeys[10]));

	        // Chuyển ma trận về chuỗi và thêm vào kết quả
	        chuoiMaHoa.append(matrixToText(state));
	    }

	    return chuoiMaHoa.toString();
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

