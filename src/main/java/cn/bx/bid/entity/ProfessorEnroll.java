package cn.bx.bid.entity;

import java.io.Serializable;

public class ProfessorEnroll implements Serializable {
     private static final long serialVersionUID = 1667314356272272646L;
     // private long id;

     private Professor prof=new Professor();
     private RandomName rand=new RandomName();

    /* public long getId() {
          return id;
     }

     public void setId(long id) {
          this.id = id;
     }*/

     public Professor getProf() {
          return prof;
     }

     public void setProf(Professor prof) {
          this.prof = prof;
     }

     public RandomName getRand() {
          return rand;
     }

     public void setRand(RandomName rand) {
          this.rand = rand;
     }

     @Override
     public String toString() {
          return "ProfessorEnroll{" +
                  "prof=" + prof +
                  ", rand=" + rand +
                  '}';
     }
}
