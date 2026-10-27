package br.mackenzie.colheita.model;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class TesteModeloJogo {
 void tick(ModeloJogo m, int n, boolean l, boolean r) { for(int i=0;i<n;i++) m.update(1f/60,l,r); }
 @Test void pauseFreezesAndRestartClears() { var m=new ModeloJogo();m.start();tick(m,120,true,false);double t=m.current().elapsed;float x=m.playerX();m.pause();tick(m,120,false,true);assertEquals(t,m.current().elapsed);assertEquals(x,m.playerX());m.start();assertEquals(0,m.totalScore());assertEquals(0,m.current().presses); }
 @Test void heldKeyCountsOnceAndBordersHold() { var m=new ModeloJogo();m.start();tick(m,300,true,false);assertEquals(1,m.current().presses);assertEquals(0,m.playerX());tick(m,1,false,false);tick(m,1,true,false);assertEquals(2,m.current().presses); }
 @Test void bothDirectionsAreNeutral() { var m=new ModeloJogo();m.start();float x=m.playerX();tick(m,60,true,true);assertEquals(x,m.playerX()); }
 @Test void allThreeLevelsFinishAndRestart() { var m=new ModeloJogo();m.start();for(int level=0;level<3;level++){tick(m,3400,false,false);assertEquals(level==2?ModeloJogo.State.FINISHED:ModeloJogo.State.PHASE_COMPLETE,m.state());if(level<2)m.nextLevel();}m.start();assertEquals(0,m.levelIndex());assertTrue(Double.isNaN(m.current().accuracy())); }
 @Test void responseAddsPoints() { var m=new ModeloJogo(new java.util.Random(42));m.start();tick(m,125,false,false);int d=m.cueDirection();assertNotEquals(0,d);tick(m,1,d<0,d>0);assertEquals(1,m.current().correct);assertTrue(m.current().responsePoints>=10); }
}
