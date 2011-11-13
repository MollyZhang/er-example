/*
 * This file is part of the PSL software.
 * Copyright 2011 University of Maryland
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package edu.umd.cs.psl.er.similarity;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import java.util.Arrays;

/*
 * Implements the dice similarity measure (quick-n-dirty version).
 * Notes:
 *	- This is not the true dice similarity.
 * 	- A better implementation might not use strings for bigrams.
 *	- The tokenizer could probably be improved.
 */
public class DiceSimilarity
{
	public static double similarity(String first, String second) {
		// Create two sets of character bigrams, one for each string.
		Set<String> s1 = splitIntoBigrams(first);
		Set<String> s2 = splitIntoBigrams(second);
		
		// Get the number of elements in each set.
		int n1 = s1.size();
		int n2 = s2.size();
				
		// Find the intersection, and get the number of elements in that set.
		s1.retainAll(s2);
		int nt = s1.size();
				
		// The coefficient is:
		// 
		//        2 ∙ | s1 ⋂ s2 |
		// D = ----------------------
		//        | s1 | + | s2 |
		// 
		return (2.0 * (double)nt) / ((double)(n1 + n2));			
	}
 
    private static Set<String> splitIntoBigrams(String s) {
    	// tokenize the input
    	String[] tokens = s.split("\\s+");
    	// create bigrams from tokens
		ArrayList<String> bigrams = new ArrayList<String>();
		if (tokens.length == 1) {
			bigrams.add(tokens[0]);
		}
		else {
			for (int i = 1; i < tokens.length; i++) {
				StringBuilder sb = new StringBuilder();
				sb.append(tokens[i-1]);
				sb.append(",");
				sb.append(tokens[i]);
				bigrams.add(sb.toString());
			}
		}
		return new TreeSet<String>(bigrams);
    }
}