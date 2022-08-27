# Printed Circuit Board
![stasiun cuaca](https://user-images.githubusercontent.com/98507357/187048247-eb1b5fed-0444-41c6-8dd6-c16f3e5ac199.JPG)
# sensor coding 

# stasiun cuaca

#include "DHT.h"
#include <WiFi.h>
#include <FirebaseESP32.h>
#define DHTPIN 22
#define DHTTYPE DHT22

DHT dht(DHTPIN, DHTTYPE);

#define WIFI_SSID "Cafe Ngoding"
#define WIFI_PASSWORD "2023punyaPT"

#define host "..."
#define key "..."

FirebaseData fbdo;
FirebaseJson jsonRealtime, jsonChart;

int windDir_pin = 35;
float h, t;

int arahAngin = 0;
int windSensor = 34;

unsigned long wsb = 0;
unsigned long wsb1 = 0;

double x = 0;
double y = 0;
double a = 0;
double b = 0;
float voltageMax = 3.3;
float voltageMin = 0.0;
float voltageConversionConstant = 0.0001;
float sensorVoltage = 0;

float windSpeedMin = 0;
float windSpeedMax = 32;

float windSpeed = 0;
int prevWindSpeed = 0;

void setup() {
  // initialize serial communication at 9600 bits per second:
  Serial.begin(115200);
  dht.begin();

  pinMode(windDir_pin, INPUT);;

  WiFi.begin(WIFI_SSID, WIFI_PASSWORD);
  Serial.print("Connecting to Wi-Fi");
  while (WiFi.status() != WL_CONNECTED)
  {
    Serial.print(".");
    delay(300);
  }
  Serial.println();
  Serial.print("Connected with IP: ");
  Serial.println(WiFi.localIP());
  Serial.println();

  Firebase.begin(host, key);
}

// the loop routine runs over and over again forever:
void loop() {
  kecepatanAngin();
  arah_angin();
  temp();

  unsigned long ws = millis();
  String pathRealtime = "/realtime/";
  if (ws - wsb >= 500) {
    jsonRealtime.set("temperature", t);
    jsonRealtime.set("kelembapan", h);
    jsonRealtime.set("kecepatanAngin", windSpeed);
    jsonRealtime.set("arahAngin", arahAngin);
    Firebase.set(fbdo, pathRealtime, jsonRealtime);
    wsb = ws;
  }

  unsigned long ws1 = millis();
  String pathChart = "/monitoring/";
  if (ws1 - wsb1 >= 60000) {
    if ( Firebase.setTimestamp(fbdo, "/timestamp/")) {
      int waktu = fbdo.to<int>();
      jsonChart.set("time", waktu);
      jsonChart.set("temp", t);
      jsonChart.set("hum", h);
      jsonChart.set("wind", windSpeed);
      jsonChart.set("dir", arahAngin);
      Firebase.push(fbdo, pathChart, jsonChart);
      wsb1 = ws1;
    }
  }
}


# arah angin


void arah_angin() {
  int sensorValue = analogRead(35);
  float voltage = sensorValue * 3.3 / 4095.0;
  arahAngin = map(sensorValue, 0, 4095, 0, 360);
  // print out the value you read:
  Serial.print("ADC : ");
  Serial.println(sensorValue);
  //  Serial.print("Voltage : ");
  //  Serial.println(voltage);
  Serial.print("Direction : ");
  Serial.println(arahAngin);
}


# kecepatan angin


void kecepatanAngin() {
  int sensorValue = analogRead(windSensor);

  float voltage = sensorValue * (3.3 / 4095.0);

  sensorVoltage = sensorValue * voltageConversionConstant;

  windSpeed = ((sensorVoltage - voltageMin) * windSpeedMax / (voltageMax / (voltageMax - voltageMin)) * 2.232694);

  Serial.println("kecepatan angin : " + String (windSpeed, 2));
  //  Serial.println("tegangan sensor : " + String(sensorVoltage));
  prevWindSpeed = windSpeed;
}

# suhu dan kelembapan

void temp() {
  h = dht.readHumidity();
  t = dht.readTemperature();
  float hic = dht.computeHeatIndex(t, h, false);

  Serial.print("Humidity: " + String(h));
  Serial.println("%  Temperature: " + String(t));

}
