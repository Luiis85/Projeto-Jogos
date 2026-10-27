package br.mackenzie.colheita.model;
import org.junit.jupiter.api.Test;
import br.mackenzie.colheita.io.ExportadorCsv;
import static org.junit.jupiter.api.Assertions.*;
class TesteModeloJogo {
 void tick(ModeloJogo m, int n, boolean l, boolean r) { for(int i=0;i<n;i++) m.update(1f/60,l,r); }
 @Test void pauseFreezesAndRestartClears() { var m=new ModeloJogo();m.start();tick(m,120,true,false);double t=m.current().elapsed;float x=m.playerX();m.pause();tick(m,120,false,true);assertEquals(t,m.current().elapsed);assertEquals(x,m.playerX());m.start();assertEquals(0,m.totalScore());assertEquals(0,m.current().presses); }
 @Test void heldKeyCountsOnceAndBordersHold() { var m=new ModeloJogo();m.start();tick(m,300,true,false);assertEquals(1,m.current().presses);assertEquals(0,m.playerX());tick(m,1,false,false);tick(m,1,true,false);assertEquals(2,m.current().presses); }
 @Test void bothDirectionsAreNeutral() { var m=new ModeloJogo();m.start();float x=m.playerX();tick(m,60,true,true);assertEquals(x,m.playerX()); }
 @Test void allThreeLevelsFinishAndRestart() { var m=new ModeloJogo();m.start();for(int level=0;level<3;level++){tick(m,3400,false,false);assertEquals(level==2?ModeloJogo.State.FINISHED:ModeloJogo.State.PHASE_COMPLETE,m.state());if(level<2)m.nextLevel();}m.start();assertEquals(0,m.levelIndex());assertTrue(Double.isNaN(m.current().accuracy())); }
 @Test void responseAddsPoints() { var m=new ModeloJogo(new java.util.Random(42));m.start();tick(m,125,false,false);int d=m.cueDirection();assertNotEquals(0,d);tick(m,1,d<0,d>0);assertEquals(1,m.current().correct);assertTrue(m.current().responsePoints>=10); }

 @Test void comandoAtivoNoFimFicaInterrompidoUmaVez() {
  var m=new ModeloJogo(new java.util.Random(42)); m.start();
  tick(m,125,false,false); assertNotEquals(0,m.cueDirection());
  int pontos=m.current().score();
  m.current().elapsed=m.level().duration()-1.0/120;
  tick(m,10,false,false);
  assertEquals(ModeloJogo.State.PHASE_COMPLETE,m.state());
  assertEquals(1,m.current().interrupted);
  assertEquals(0,m.current().wrong); assertEquals(0,m.current().timedOut);
  assertEquals(pontos,m.current().score());
  m.nextLevel(); assertEquals(0,m.current().interrupted);
  assertEquals(1,m.stats().get(0).interrupted);
  m.start(); assertTrue(m.stats().stream().allMatch(s->s.interrupted==0));
 }

 @Test void respostaCorretaNaoContaComoInterrompida() {
  var m=new ModeloJogo(new java.util.Random(42)); m.start();
  tick(m,125,false,false); int d=m.cueDirection();
  tick(m,1,d<0,d>0);
  m.current().elapsed=m.level().duration()-1.0/120;
  tick(m,2,false,false);
  assertEquals(1,m.current().correct); assertEquals(0,m.current().interrupted);
 }

 @Test void prazoEsgotadoContinuaComoSemResposta() {
  var m=new ModeloJogo(new java.util.Random(42)); m.start();
  tick(m,310,false,false); assertEquals(1,m.current().timedOut);
  m.current().elapsed=m.level().duration()-1.0/120;
  tick(m,2,false,false);
  assertEquals(1,m.current().timedOut); assertEquals(0,m.current().interrupted);
 }

 @Test void csvClassificaTodosOsComandosAoConcluirAsTresFases() {
  var m=new ModeloJogo(new java.util.Random(42)); m.start();
  for(int fase=0;fase<3;fase++) {
   tick(m,125,false,false);
   m.current().elapsed=m.level().duration()-1.0/120;
   tick(m,10,false,false);
   if(fase<2)m.nextLevel();
  }
  String[] linhas=ExportadorCsv.content(m).strip().split("\\R");
  assertEquals(4,linhas.length);
  assertTrue(linhas[0].endsWith(";interrompidos_fim_fase"));
  for(int fase=1;fase<=3;fase++) {
   String[] campos=linhas[fase].split(";",-1);
   assertEquals(18,campos.length); assertEquals("1",campos[17]);
   assertEquals(Integer.parseInt(campos[7]), Integer.parseInt(campos[8])
    +Integer.parseInt(campos[9])+Integer.parseInt(campos[10])+Integer.parseInt(campos[17]));
  }
 }
}
