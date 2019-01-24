import java.io.*;

class FullName {
	String name;
	public FullName(String temp) {
		name = temp;
	}
	
	public String nameInRequiredFormat() {
		String initials = "";

		String words[] = name.split(" ");
		for (int i=0; i<words.length - 1; i++) {
			initials += String.valueOf(words[i].charAt(0)).toUpperCase() + ". ";
		}
		
		initials += String.valueOf(words[words.length - 1].charAt(0)).toUpperCase() + words[words.length - 1].substring(1);

		return initials;
	}
}

class FormatName {
	public static void main(String args[]) {
		System.out.print("Enter a name :");
		BufferedReader br = null;
		FullName fn = null;
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			fn = new FullName(br.readLine());
		} catch(Exception e) {
			System.out.println(e);
		}
		System.out.println("Initials: " + fn.nameInRequiredFormat());
	}
}