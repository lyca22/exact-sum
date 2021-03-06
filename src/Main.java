import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/*
 * ----USE THIS ONE IF YOU WANT TO SUBMIT IT TO ONLINEJUDGE.ORG----
 * This class uses an InputStreamReader instead.
 * Error lines not updated.
 */

public class Main {

	private static final String ERROR_LINE = "An error ocurred. Please make sure you have Input.txt inside the project's folder and that you have permission to open the file.";
	private static final String ERROR_LINE_2= "The file's content isn't valid (Input.txt), please check if you wrote it correctly. Example:\n2\n40 40\n80";
	static int n;
	static String[] caseList;
	static int[] caseListInt;
	static int m;
	static int difference;
	static int book1;
	static int book2;

	public static void main(String[] args) {
		try {
			importData("Input.txt");
		} catch (IOException e) {
			System.out.println(ERROR_LINE);
		} catch (NullPointerException npe) {
			System.out.println(ERROR_LINE);
		} catch (NumberFormatException nfe) {
			System.out.println(ERROR_LINE_2);
		}
	}

	public static void importData(String fn) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String line = br.readLine();
		while(line != null) {
			n = Integer.parseInt(line);
			line = br.readLine();
			String cases = line;
			caseList = cases.split(" ");
			line = br.readLine();
			m = Integer.parseInt(line);
			caseListInt = convertStringArrToIntArr(caseList);
			Arrays.sort(caseListInt);
			searchBooks();
			bw.write("Peter should buy books whose prices are " + book1 + " and " + book2 + ".\n\n");
			line = br.readLine();
			line = br.readLine();
		}
		br.close();
		bw.close();
	}

	public static int[] convertStringArrToIntArr(String[] stringArray) throws NullPointerException {
		int[] result = new int[stringArray.length];
		for(int i = 0; i < stringArray.length; i++) {
			result[i] = Integer.parseInt(stringArray[i]);
		}
		return result;
	}

	public static void searchBooks() {
		int localDifference = 0;
		int count = 0;
		for(int i = 0; i < caseListInt.length; i++) {
			int searchedValue = (m - caseListInt[i]);
			int index = binarySearch(caseListInt, searchedValue);
			if(index != -1) {
				localDifference = Math.abs(caseListInt[i] - caseListInt[index]);
				if((localDifference < difference || count == 0) && i != index) {
					book1 = caseListInt[i];
					book2 = caseListInt[index];
					difference = localDifference;
					count++;
				}
			}
		}
	}

	public static int binarySearch(int[] List, int value) {
		int pos = -1;
		int i = 0;
		int j = List.length-1;

		while(i <= j && pos < 0) {
			int middle = (i+j)/2;
			if(List[middle] == value) {
				pos = middle;
			}else if(List[middle] > value) {
				j = middle-1;
			}else {
				i = middle+1;
			}
		}
		return pos;
	}

}
