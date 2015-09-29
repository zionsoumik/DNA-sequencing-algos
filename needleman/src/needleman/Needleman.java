/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package needleman;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Timer;

/**
 *
 * @author Soumik
 */
public class Needleman {
char[] mSeqA;
        char[] mSeqB;
        int[][] mD;
        int mScore;
        String mAlignmentSeqA = "";
        String mAlignmentSeqB = "";
        
        void init(char[] seqA, char[] seqB) {
                mSeqA = seqA;
                mSeqB = seqB;
                mD = new int[mSeqA.length + 1][mSeqB.length + 1];
                for (int i = 0; i <= mSeqA.length; i++) {
                        for (int j = 0; j <= mSeqB.length; j++) {
                                if (i == 0) {
                                        mD[i][j] = -j;
                                } else if (j == 0) {
                                        mD[i][j] = -i;
                                } else {
                                        mD[i][j] = 0;
                                }
                        }
                }
        }
        
        void process() {
                for (int i = 1; i <= mSeqA.length; i++) {
                        for (int j = 1; j <= mSeqB.length; j++) {
                                int scoreDiag = mD[i-1][j-1] + weight(i, j);
                                int scoreLeft = mD[i][j-1] - 1;
                                int scoreUp = mD[i-1][j] - 1;
                                mD[i][j] = Math.max(Math.max(scoreDiag, scoreLeft), scoreUp);
                        }
                }
        }
        
        void backtrack() {
            
                int i = mSeqA.length;
                int j = mSeqB.length;
                mScore = mD[i][j];
                while (i > 0 && j > 0) {                        
                        if (mD[i][j] == mD[i-1][j-1] + weight(i, j)) {                          
                                mAlignmentSeqA += mSeqA[i-1];
                                mAlignmentSeqB += mSeqB[j-1];
                                i--;
                                j--;                            
                                continue;
                        } else if (mD[i][j] == mD[i][j-1] - 1) {
                                mAlignmentSeqA += "-";
                                mAlignmentSeqB += mSeqB[j-1];
                                j--;
                                continue;
                        } else {
                                mAlignmentSeqA += mSeqA[i-1];
                                mAlignmentSeqB += "-";
                                i--;
                                continue;
                        }
                }
                mAlignmentSeqA = new StringBuffer(mAlignmentSeqA).reverse().toString();
                mAlignmentSeqB = new StringBuffer(mAlignmentSeqB).reverse().toString();
        }
        
        private int weight(int i, int j) {
                if (mSeqA[i - 1] == mSeqB[j - 1]) {
                        return 1;
                } else {
                        return -1;
                }
        }
        
        void printMatrix() {
                System.out.println("D =");
                for (int i = 0; i < mSeqA.length + 1; i++) {
                        for (int j = 0; j < mSeqB.length + 1; j++) {
                                System.out.print(String.format("%4d ", mD[i][j]));
                        }
                        System.out.println();
                }
                System.out.println();
        }
        
        void printScoreAndAlignments(double dur1,double dur2) throws SQLException {
            putseq s=new putseq();
            s.intodb(mScore,mAlignmentSeqA,mAlignmentSeqB,dur1,dur2);
                System.out.println("Score: " + mScore);
                System.out.println("Sequence A: " + mAlignmentSeqA);
                System.out.println("Sequence B: " + mAlignmentSeqB);
                System.out.println();
        }
        
@SuppressWarnings("empty-statement")
        public static void main(String [] args) throws SQLException {               
                
               putdata p=new putdata();
               getdata g=new getdata();
               String f;
               Random r=new Random();
                int random1=r.nextInt(4);
                 
               //char[] seqB={'A','T','G','C'};
                String alphabet="ATGC";
             
                  String f1=alphabet.substring(random1,random1+1);  
               for(int j=1;j<50;j++){
                   f1=f1+alphabet.charAt(r.nextInt(alphabet.length()));
               }
               
              char seqB[]=f1.toCharArray();
               p.intodb();
               ArrayList arr=new ArrayList();
               arr=g.fromdb();
               for(int i=0;i<arr.size();i++){
                   f=(String) arr.get(i);
                   char[] seqA=f.toCharArray();
               Needleman nw = new Needleman();
                nw.init(seqA, seqB);
                double startTime = System.nanoTime();
nw.process();
double endTime = System.nanoTime();

double duration = (endTime - startTime);
double g1=duration/10000;
           System.out.println(g1);     
                long startTime1 = System.nanoTime();
nw.backtrack();
double endTime1 = System.nanoTime();

double duration2 = (endTime1 - startTime1);
double g2=duration2/10000;
  System.out.println(g2);              
                
                nw.printMatrix();
                nw.printScoreAndAlignments(g1,g2);}
        }
}