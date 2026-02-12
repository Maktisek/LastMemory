# 📦 LAST MEMORY


> *Last Memory je textová hra vytvořená za účelem získání nových zkušeností v programování jako takovém.*


## 🌟 Důležité body

- Hra obsahuje zajímavý příběh, který lze postupně odhalovat
- Gameplay je snadný a svižný
- Hra implementuje zvukový systém, který hře dodává atmosféru
- Textové UI je přehledné a barvami zvýrazněné
- Text je často doprovázen ASCII arty


## ℹ️ Přehled
Textová hra *Last Memory* vznikla v rámci školního projektu, jehož zadáním bylo
napsat textovou hru v jazyce Java☕

Mým cílem nebylo jen "splnit" zadání, ale posunout hru na další level. Snažil jsem se tedy 
psát kód co nejvíce čistě, chytře a plánovaně🧠 

Tímto jsem posunul své zkušenosti na další level a naučil jsem se plno nových 
a skvěle využitelných skillů⬆️

### ✍️ O autorovi

Jsem [Matěj Pospíšil](https://github.com/Maktisek) a nyní jsem ve druhém ročníku na střední elektrotechnické škole v Ječné📖 

Programuji v jazyce Java a v budoucnu budu programovat v jazycích C# a Python. 


## ⬇️ Instalační a spouštěcí proces
Ke spustění *Last Memory* potřebujeme systém
- s nainstalovanou Javou (verze 17 a výše)☕
- s funkční příkazovou řádkou (např. CMD, Windows PowerShell)🔧

Pokud se bojíte výkonu vašeho PC, nebojte, hra pojede i na bramboře🥔

### 🚀 Rychlé spuštění
1. Nainstaluj Java 17+
2. Otevři terminál ve složce s hrou
3. Povol UTF-8 (příkaz: chcp 65001)
4. spusť:
```cmd
java -Dfile.encoding=UTF-8 -jar LastMemory.jar
```

### 🧐 Instalace a umístění JAR souboru
A nyní pomaleji...

Pro hraní hry *Last Memory* je třeba mít na svém PC nainstalovaný herní JAR soubor.
Tento JAR soubor je třeba uložit do libovolné složky v počítači📁

### 🙅🏻‍♀️ Co nedělat
Protože se jedná o textovou hru, nelze ji spustit v programu *Java(TM) Platform SE binary*❌

Místo toho si otevřte příkazový řádek. Zde bude celá hra probíhat✅

### 🛠️ Příprava příkazového řádku
Prvně bude třeba příkazovou řádku "naučit" znakové kódování UTF-8. 
Tohle ale není problém, protože stačí do příkazové řádky napsat:
```cmd
chcp 65001
```
Toto změní znakové kódovaní na chtěné UTF-8📝

### ➡️ Nastavení cesty pro spuštění hry
Vzhledem k tomu, že příkazový řádek stále neví odkud budeme spouštět, musíme mu to říct.

Prvně si budeme muset hru najít v průzkumníku souborů. Zkontrolujeme si, na jakém disku se soubor nachází.

Příkazový řádek říká, kde hledá. Pokud disk nesedí napište název disku a dvojtečku.

Například chceme přepnout na disk D, pak napíšeme:
```cmd
D:
```

Pokud disk sedí, není potřeba dělat nic❗

Nyní už jen nastavit cestu. Dejme tomu, že herní JAR soubor se nachází na: D:\Test.
Pak do příkazového řádku napíšeme:
```cmd
cd D:\Test
```
Nyní příkazový řádek hledá v požadované složce, která obsahuje JAR soubor✅

### ▶️ Spuštění hry
Nyní poslední krok - spustit hru⚡

Do příkazové řádky napište:
```cmd
java -Dfile.encoding=UTF-8 -jar LastMemory.jar
```
Bez *-Dfile.encoding=UTF-8* by mohlo dojít k problémům při zadávání vstupů do herní konzole.

Pokud se JAR soubor jmenuje jinak, přepište LastMemory na jméno souboru.

A je hotovo, hra běží🎉

## 💻 Jak hru ovládat
Hra s vámi komunikuje prostřednictvím textu. Vždy oznamuje, kde se nacházíte a co se právě děje.

Pokud chcete vykonat libovolnou akci, pak musíte napsat její příkazový klíč. Jak se ale dozvíme tento klíč?

### 🆘 Příkaz pomoc
Pokud napíšete příkaz pomoc, hra automaticky vypíše, co lze v daný moment dělat. 

Systém je to snadný:
"název příkazu" -> "upřesnění" = co provede. Vždy před druhým vstupem hra vypíše informaci o možnostech.

S touto radou se nestratíte🌟

## 💻 Jak hru hrát
Po celou dobu gameplaye máte možnost zavolat příkaz "jak hrát". Ten detailně
popíše:
- jak se hra hraje✅
- co je cílem🎯
- nejlepší strategie🧠

## 🔎 Závěr
Na hru *Last Memory* jsem velmi pyšný a sám bych nečekal, že uvidím tolik zlepšení.
Budu si vážit tvého času, který strávíš hraním mé hry.

Věřím, že v budoucnu stvořím ještě větší projekty✨
