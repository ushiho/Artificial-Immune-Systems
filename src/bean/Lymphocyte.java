/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.util.Arrays;
import java.util.BitSet;
import java.util.Objects;

/**
 *
 * @author lotfi
 */
public class Lymphocyte {
    
    private BitSet antibody; // Detector
    private int[] searchTable; // Detects method to greatly increase performance.
    private int stimulation; //counter that tracks how many times a Lymphocyte object has detected a possible threat

    public Lymphocyte() {
    }


    public Lymphocyte(BitSet antibody, int[] searchTable, int stimulation) {
        this.antibody = antibody;
        this.searchTable = searchTable;
        this.stimulation = stimulation;
    }
    
    public BitSet getAntibody() {
        return antibody;
    }

    public void setAntibody(BitSet antibody) {
        this.antibody = antibody;
    }

    public int[] getSearchTable() {
        return searchTable;
    }

    public void setSearchTable(int[] searchTable) {
        this.searchTable = searchTable;
    }

    public int getStimulation() {
        return stimulation;
    }

    public void setStimulation(int stimulation) {
        this.stimulation = stimulation;
    }

    @Override
    public int hashCode(){
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.antibody);
        hash = 47 * hash + Arrays.hashCode(this.searchTable);
        hash = 47 * hash + this.stimulation;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Lymphocyte other = (Lymphocyte) obj;
        if (this.stimulation != other.stimulation) {
            return false;
        }
        if (!Objects.equals(this.antibody, other.antibody)) {
            return false;
        }
        return Arrays.equals(this.searchTable, other.searchTable);
    }
    
    public String toString(int numAntibodyBits) {
      String s = "antibody = ";
      for (int i = 0; i < numAntibodyBits; ++i)
        s += (antibody.get(i) == true) ? "1 " : "0 ";
      s += "  stimulation = " + stimulation;
      return s;
    }
    
}
