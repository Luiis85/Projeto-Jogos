const int botaoBranco = 2;
const int botaoAzul = 3;
const unsigned long filtroMs = 25;
const unsigned long envioMs = 50;

struct Botao {
  int pino;
  bool amostra;
  bool pressionado;
  unsigned long desde;
};

Botao branco = {botaoBranco, false, false, 0};
Botao azul = {botaoAzul, false, false, 0};
unsigned long ultimoEnvio = 0;

void atualizar(Botao &botao, unsigned long agora);

void atualizar(Botao &botao, unsigned long agora) {
  bool leitura = digitalRead(botao.pino) == LOW;
  if (leitura != botao.amostra) {
    botao.amostra = leitura;
    botao.desde = agora;
  }
  if (agora - botao.desde >= filtroMs) {
    botao.pressionado = botao.amostra;
  }
}

void setup() {
  pinMode(botaoBranco, INPUT_PULLUP);
  pinMode(botaoAzul, INPUT_PULLUP);
  Serial.begin(9600);
}

void loop() {
  unsigned long agora = millis();
  atualizar(branco, agora);
  atualizar(azul, agora);

  if (agora - ultimoEnvio >= envioMs) {
    ultimoEnvio = agora;
    Serial.print("Branco: ");
    Serial.print(branco.pressionado ? "APERTADO" : "SOLTO");
    Serial.print(" | Azul: ");
    Serial.println(azul.pressionado ? "APERTADO" : "SOLTO");
  }
}
