package learn.String;

import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
	
	/**
				.	Matches any single character
				^aaa	Matches aaa regex at the beginning of the line
				aaa$	Matches regex aaa at the end of the line
				[abc]	Can match any of the letter a, b or c
				[abc][12]	Can match a, b or c followed by 1 or 2
				[^abc]	When ^ is the first character in [], it negates the pattern, matches anything except a, b or c
				[a-e1-8]	Matches ranges between a to e or 1 to 8	(“[a-e1-3].”, “d#”) – true(“[a-e1-3]”, “2”) – true....................(“[a-e1-3]”, “f2”) – false
				xx|yy	Matches regex xx or yy	(“x.|y”, “xa”) – true(“x.|y”, “y”) – true (“x.|y”, “yz”) – false

			 	x?	x occurs once or not at all
				X*	X occurs zero or more times
				X+	X occurs one or more times
				X{n}	X occurs exactly n times
				X{n,}	X occurs n or more times
				X{n,m}	X occurs at least n times but not more than m times
				
				\d	Any digits, short of [0-9]
				\D	Any non-digit, short for [^0-9]
				\s	Any whitespace character, short for [\t\n\x0B\f\r]
				\S	Any non-whitespace character, short for [^\s]
				\w	Any word character, short for [a-zA-Z_0-9]
				\W	Any non-word character, short for [^\w]
				\b	A word boundary
				\B	A non word boundary
	 */

	public static void main(String[] args) {
		String regex = "apple \\w* orange";
		String value = "kiwi apple banana orange apple something apple kiwi orange";
		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
	    Matcher matcher = pattern.matcher(value);
	    int begin =0;
	    int end =value.length()-1;
	    while(matcher.find()) {
	    	System.out.println(value.substring(begin,matcher.start()));
	    	begin = matcher.end();
	    }
	    if(end!=matcher.end()) {
	    	System.out.println(begin);
	    }

	}

}
