//package sk.uniza.fri;
//
//import java.util.ArrayList;
//
///**
// * 30. 3. 2022 - 13:05
// *
// * @author tmssa
// */
//public class MonotOcislovanie {
//    private final Graf graf;
//
//    public MonotOcislovanie(Graf graf) {
//        this.graf = graf;
//        this.graf.addHranyToVrcholDiGraph();
//    }
//    public ArrayList<Vrchol> dajMonotonneOcislovanie() {
//        ArrayList<Vrchol> monotonickeOcislovanie = new ArrayList<>();
//       int[] ideg = new int[this.graf.getZoznamVrcholov().size()];
//       for (int i = 0; i < this.graf.getZoznamVrcholov().size(); i++) {
//           ideg[i] = this.graf.getZoznamVrcholov().get(i).getVstupneHrany().size();
//       }
//        boolean pokracuj = true;
//        while (pokracuj) {
//           Vrchol riadiaciVrchol = null;
//           for (int i = 0; i < this.graf.getZoznamVrcholov().size(); i++) {
//               Vrchol v = this.graf.getZoznamVrcholov().get(i);
//               if (ideg[i] == 0 && !v.isOcislovany()) {
//                   riadiaciVrchol = v;
//                   break;
//               }
//           }
//           if (riadiaciVrchol == null) {
//               pokracuj = false;
//           } else {
//               monotonickeOcislovanie.add(riadiaciVrchol);
//               riadiaciVrchol.setOCislovany(true);
//               for (Hrana hrana : riadiaciVrchol.getVytupneHrany()) {
//                   ideg[hrana.getVrcholDo()]--;
//               }
//           }
//       }
//        if (monotonickeOcislovanie.size() == this.graf.getPocetVrchlov() - 1) {
//            System.out.println("Digraf je acyklický");
//        } else {
//            System.out.println("Digraf nie je acyklický");
//        }
//    }
//}
