/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Smith-Waterman local alignment algorithm.
 */
package fasta;

import java.io.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Stack;
import java.util.ArrayList;
import java.util.Random;



   
	
   


/**
 *
 * @author Soumik
 */
public class Fasta {
 private String one, two;
	private int matrix[][];
	private int gap;
	private int match;
	private int o;
	private int l;
	private int e;
        private String align1;
        private String align2;

    public String getAlign1() {
        return align1;
    }

    public void setAlign1(String align1) {
        this.align1 = align1;
    }

    public String getAlign2() {
        return align2;
    }

    public void setAlign2(String align2) {
        this.align2 = align2;
    }
        

    public double getDuration1() {
        return duration1;
    }

    public void setDuration1(double duration1) {
        this.duration1 = duration1;
    }

    public double getDuration2() {
        return duration2;
    }

    public void setDuration2(double duration2) {
        this.duration2 = duration2;
    }
        private double duration1;
        private double duration2;
	public Fasta(String one, String two) {
		this.one = "-" + one.toUpperCase();
		this.two = "-" + two.toUpperCase();
		this.match = 2;

		// Define affine gap starting values
		o = -2;
		l = 0;
		e = -1;

		// initialize matrix to 0
		matrix = new int[one.length() + 1][two.length() + 1];
		for (int i = 0; i < one.length(); i++)
			for (int j = 0; j < two.length(); j++)
				matrix[i][j] = 0;

	}

	// returns the alignment score
	public int computeFasta() {
            double startTime1 = System.nanoTime();
		for (int i = 0; i < one.length(); i++) {
			for (int j = 0; j < two.length(); j++) {
				gap = o + (l - 1) * e;
				if (i != 0 && j != 0) {
					if (one.charAt(i) == two.charAt(j)) {
						// match
						// reset l
						l = 0;
						matrix[i][j] = Math.max(0, Math.max(
								matrix[i - 1][j - 1] + match, Math.max(
										matrix[i - 1][j] + gap,
										matrix[i][j - 1] + gap)));
					} else {
						// gap
						l++;
						matrix[i][j] = Math.max(0, Math.max(
								matrix[i - 1][j - 1] + gap, Math.max(
										matrix[i - 1][j] + gap,
										matrix[i][j - 1] + gap)));
					}
				}
			}
		}

		// find the highest value
		int longest = 0;
		int iL = 0, jL = 0;
		for (int i = 0; i < one.length(); i++) {
			for (int j = 0; j < two.length(); j++) {
				if (matrix[i][j] > longest) {
					longest = matrix[i][j];
					iL = i;
					jL = j;
				}
			}
		}
   double endTime1 = System.nanoTime();

double  duration1 = (endTime1 - startTime1)/10000;
System.out.println(duration1);
		// Backtrack to reconstruct the path
                 double startTime = System.nanoTime();


		int i = iL;
		int j = jL;
		Stack<String> actions = new Stack<String>();

		while (i != 0 && j != 0) {
			// diag case
			if (Math.max(matrix[i - 1][j - 1],
					Math.max(matrix[i - 1][j], matrix[i][j - 1])) == matrix[i - 1][j - 1]) {
				actions.push("align");
				i = i - 1;
				j = j - 1;
				// left case
			} else if (Math.max(matrix[i - 1][j - 1],
					Math.max(matrix[i - 1][j], matrix[i][j - 1])) == matrix[i][j - 1]) {
				actions.push("insert");
				j = j - 1;
				// up case
			} else {
				actions.push("delete");
				i = i - 1;
			}
		}

		String alignOne = new String();
		String alignTwo = new String();

		Stack<String> backActions = (Stack<String>) actions.clone();
		for (int z = 0; z < one.length(); z++) {
			alignOne = alignOne + one.charAt(z);
			if (!actions.empty()) {
				String curAction = actions.pop();
				// System.out.println(curAction);
				if (curAction.equals("insert")) {
					alignOne = alignOne + "-";
					while (actions.peek().equals("insert")) {
						alignOne = alignOne + "-";
						actions.pop();
					}
				}
			}
		}

		for (int z = 0; z < two.length(); z++) {
			alignTwo = alignTwo + two.charAt(z);
			if (!backActions.empty()) {
				String curAction = backActions.pop();
				if (curAction.equals("delete")) {
					alignTwo = alignTwo + "-";
					while (backActions.peek().equals("delete")) {
						alignTwo = alignTwo + "-";
						backActions.pop();
					}
				}
			}
		}
double endTime = System.nanoTime();

double  duration = (endTime - startTime)/10000;
System.out.println(duration);
this.setDuration1(duration1);
this.setDuration2(duration);
		// print alignment
this.setAlign1(alignOne);
this.setAlign2(alignTwo);
		System.out.println(alignOne + "\n" + alignTwo);
		return longest;
	}

	public void printMatrix() {
		for (int i = 0; i < one.length(); i++) {
			if (i == 0) {
				for (int z = 0; z < two.length(); z++) {
					if (z == 0)
						System.out.print("   ");
					System.out.print(two.charAt(z) + "  ");

					if (z == two.length() - 1)
						System.out.println();
				}
			}

			for (int j = 0; j < two.length(); j++) {
				if (j == 0) {
					System.out.print(one.charAt(i) + "  ");
				}
				System.out.print(matrix[i][j] + "  ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void main(String[] args) throws SQLException {
		// DNA sequence Test:
            putdata p=new putdata();
               getdata g=new getdata();
               String f;
               putseq t=new putseq();
               p.intodb();
               Random r=new Random();
                String alphabet="ATGC";
              int random1=r.nextInt(4);
                  String f1=alphabet.substring(random1,random1+1);  
               for(int j=1;j<50;j++){
                   f1=f1+alphabet.charAt(r.nextInt(alphabet.length()));
               }
             
              String seqB=f1;
            int score=0;
               ArrayList arr=new ArrayList();
               arr=g.fromdb();
               for(int i=0;i<arr.size();i++){
                   f=(String) arr.get(i);
                   String seqA=f.toString();
               Fasta nw = new Fasta(seqA, seqB);
               score=nw.computeFasta();
               t.intodb(score,nw.getAlign1(), nw.getAlign2(),nw.getDuration1(),nw.getDuration2());
               System.out.println("Alignment Score: " + score);
nw.printMatrix();

             
                
                
                

	}
    
}
}
