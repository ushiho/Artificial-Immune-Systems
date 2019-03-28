/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import bean.Lymphocyte;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Random;
import static service.ImmuneSystemService.bitArrayAsString;
import static service.ImmuneSystemService.loadSelfSet;
import static service.ImmuneSystemService.showSelfSet;
import service.LymphocyteService;

/**
 *
 * @author lotfi
 */
public class Test {
    
    @SuppressWarnings("empty-statement")
    public static void main(String[] args) {
        
        System.out.println("\nBegin Artificial Immune System for Intrusion" +
        " Detection demo\n");
        Random random = new Random(1);
        int numPatternBits = 12;
        int numAntibodyBits = 4;
        int numLymphocytes = 3;
        int stimulationThreshold = 3;
        System.out.println("Loading self-antigen set ('normal' historical patterns)");
        ArrayList<BitSet> selfSet = loadSelfSet(null);
        showSelfSet(selfSet);
        System.out.println("\nCreating lymphocyte set using negative selection and r-chunks detection");
        // Creates 3 lymphocytes with an antibody of length 4 bits
        ArrayList<Lymphocyte> lymphocyteSet = LymphocyteService.createLymphocyteSet(selfSet, numAntibodyBits, numLymphocytes);
        System.out.println("^^^ Show lymphocytSet ^^^");
        LymphocyteService.showLymphocyteSet(lymphocyteSet, numAntibodyBits);
        System.out.println("\nBegin AIS intrusion detection simulation\n");
        int time = 0;
        int maxTime = 6;
        while (time < maxTime){
            
            System.out.println("============================================");
            BitSet incoming = LymphocyteService.randomBitArray(numPatternBits);
            System.out.println("Incoming pattern = " + bitArrayAsString(incoming, numPatternBits) + "\n");
            
            for (int i = 0; i < lymphocyteSet.size(); ++i){
                Lymphocyte lymphocyte = lymphocyteSet.get(i);
              if (LymphocyteService.detects(incoming, lymphocyte.getAntibody(), lymphocyte.getSearchTable())){
                  
                  System.out.println("Incoming pattern detected by lymphocyte " + i);
                  lymphocyte.setStimulation(lymphocyte.getStimulation()+1);
                if (lymphocyte.getStimulation() >= stimulationThreshold)
                      System.out.println("Lymphocyte " + i + " stimulated! Check incoming as possible intrusion!");
                else
                      System.out.println("Lymphocyte " + i + " not over stimulation threshold");;
              }
              else
                    System.out.println("Incoming pattern not detected by lymphocyte " + i);
            }
            ++time;
            System.out.println("============================================");
        
        }
          
        System.out.println("\nEnd AIS IDS demo\n");
        System.out.println();
      }
}
