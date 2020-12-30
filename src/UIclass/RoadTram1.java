package UIclass;

import javafx.scene.Group;

public class RoadTram1 extends Group {
    final private SimpleRoadTram simpleRoadTramTopRight = new SimpleRoadTram(0, 200, 550);
    final private SimpleRoadTram simpleRoadTramTopLeft = new SimpleRoadTram(730, 200, 550);
    final private ObliqueRoadTram obliqueRoadTramTopLeft = new ObliqueRoadTram(730, 185, 160);
    final private ObliqueRoadTram obliqueRoadTramTopRight = new ObliqueRoadTram(530, 185, 160);

   public RoadTram1(){
       obliqueRoadTramTopLeft.getGroup().setRotate(180);
       this.getChildren().add(0, simpleRoadTramTopRight);
       this.getChildren().add(1, obliqueRoadTramTopRight);
       this.getChildren().add(2, obliqueRoadTramTopLeft);
       this.getChildren().add(3, simpleRoadTramTopLeft);
   }
}
