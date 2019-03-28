/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;
import java.util.Random;
import bean.Lymphocyte;
import service.LymphocyteService;

/**
 *
 * @author lotfi
 */
public class ImmuneSystemService {
    
    // num Bits
    public static int N_BITS = 12;
    
    // Creates an artificial, static self-set.
    // self-set = self-antigens are normals non-harmful datas.
    public static ArrayList<BitSet> loadSelfSet(String datasource){
        ArrayList<BitSet> result = new ArrayList<>();
        boolean[] self0 = new boolean[]{ true, false, false, true, false, true, true, false, true, false, false, true };
        boolean[] self1 = new boolean[]{ true, true, false, false, true, false, true, false, true, true, false, false };
        boolean[] self2 = new boolean[]{ true, false, true, true, false, false, true, true, false, true, false, true };
        boolean[] self3 = new boolean[]{ false, false, true, true, false, true, false, true, true, false, true, true };
        boolean[] self4 = new boolean[]{ false, true, false, true, false, true, false, false, true, true, false, true };
        boolean[] self5 = new boolean[]{ false, false, true, false, true, false, true, false, false, true, false, false };

        result.add(createBitSet(self0, N_BITS));
        result.add(createBitSet(self1, N_BITS));
        result.add(createBitSet(self2, N_BITS));
        result.add(createBitSet(self3, N_BITS));
        result.add(createBitSet(self4, N_BITS));
        result.add(createBitSet(self5, N_BITS));

        return result;
        
    }
    
    public static BitSet createBitSet(boolean[] bits, int numBits){
        BitSet bs = new BitSet();
        for (int i = 0; i < numBits; i++){
            bs.set(numBits - i - 1, bits[i]);
        }

        return bs;
    }
    
    public static void showSelfSet(List<BitSet> selfSet){
        
        for (int i = 0; i < selfSet.size(); ++i){
            System.out.println("BitSet["+i+"] => "+bitArrayAsString(selfSet.get(i), N_BITS));
            System.out.println();
        }
      }
    
    public static String bitArrayAsString(BitSet bs, int numBits){
        
      String s = "";
      for (int i = 0; i < numBits; ++i)
        s += (bs.get(i) == true) ? "1 " : "0 ";
      return s;// creates 3 lymphocytes with an antibody of length 4bits
    }
    
    
    
    /*
     * *** NOT USED *** *
    */
    public static void setRandomBits(BitSet b) {
        
        Random r = new Random();
        for (int i = 0; i < N_BITS / 2; i++){
            b.set(r.nextInt(N_BITS));
        }
    }

    public static void printBits(String prompt, BitSet b) {
        
       System.out.print(prompt + " ");
       for (int i = 0; i < N_BITS; i++) {
          System.out.print(b.get(i) ? "1" : "0");
       }
       System.out.println();
    }
    
    
}
