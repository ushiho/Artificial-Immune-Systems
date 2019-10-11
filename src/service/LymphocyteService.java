/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.ArrayList;
import java.util.BitSet;
import bean.Lymphocyte;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


/**
 *
 * @author lotfi
 */
public class LymphocyteService {
    
    // Lymphocyte set should contain only Lymphocyte objects that do not detect any patterns in the self-set.
    public static ArrayList<Lymphocyte> createLymphocyteSet(ArrayList<BitSet> selfSet,int numAntibodyBits, int numLymphocytes){
        
        ArrayList<Lymphocyte> result = new ArrayList<>();
        Map<Integer, Boolean> contents = new HashMap<>();
        
        while (result.size() < numLymphocytes){
            
          BitSet antibody = randomBitArray(numAntibodyBits);
          Lymphocyte lymphocyte = new Lymphocyte(antibody, buildTable(antibody, numAntibodyBits), 0);
          int hash = lymphocyte.hashCode();
          if (detectsAny(selfSet, lymphocyte) == false &&
            contents.containsKey(hash) == false)
          {
            result.add(lymphocyte);
            contents.put(hash, true);
          }
        }
        return result;
      }
    
    
    /*
    A random Lymphocyte object is generated and then tested to see if it does not detect any patterns in the self-set,
    and also that the lymphocyte is not already in the result set.
    */
    public static BitSet randomBitArray(int numBits){
          boolean[] bools = new boolean[numBits];
          
          for (int i = 0; i < numBits; ++i)
          {
            int b = new Random().nextInt(2);  // between [0,1] inclusive
            bools[i] = (b != 0);
          }
          
          return ImmuneSystemService.createBitSet(bools, numBits);
    }
    
    /*
    detectsAny accepts a self-set, and a lymphocyte scans through a self-set 
    and returns true if any pattern in the self-set is detected by the antigen in the lymphocyte
    */
    private static boolean detectsAny(ArrayList<BitSet> selfSet, Lymphocyte lymphocyte){
        
      for (int i = 0; i < selfSet.size(); ++i)
        if (detects(selfSet.get(i), lymphocyte.getAntibody(), lymphocyte.getSearchTable()) == true) return true;
      
      return false;
    }

    // BuildTable to create the searchTable field.
    public static int[] buildTable(BitSet antibody, int numAntibodyBits){
        
      int[] result = new int[numAntibodyBits];
      int pos = 2;
      int cnd = 0;
      result[0] = -1;
      result[1] = 0;
      while (pos < numAntibodyBits)
      {
        if (antibody.get(pos -1) == antibody.get(cnd))
        {
          ++cnd; result[pos] = cnd; ++pos;
        }
        else if (cnd > 0)
          cnd = result[cnd];
        else
        {
          result[pos] = 0; ++pos;
        }
      }
      return result;
    }

    // does the this.antibody detector detect pattern?
    // Knuth-Morris-Pratt algorithm aka r-chunks
    public static boolean detects(BitSet pattern, BitSet antibody, int[] searchTable){
      
      int m = 0;
      int i = 0;
      while (m + i < pattern.length())
      {
        if (antibody.get(i) == pattern.get(i+ 1))
        {
          if (i == antibody.length() - 1)
            return true;
          ++i;
        }
        else
        {
          m = m + i - searchTable[i];
          if (searchTable[i] > -1)
            i = searchTable[i];
          else
            i = 0;
        }
      }
      return false;  // not found
    }
    
    // Display the generated Lymphocyte objects.
    public static void showLymphocyteSet(ArrayList<Lymphocyte> lymphocyteySet, int numAntibodyBits){
        
        for (int i = 0; i < lymphocyteySet.size(); ++i)
            System.out.println(i + ": " + lymphocyteySet.get(i).toString(numAntibodyBits));
      }


}
