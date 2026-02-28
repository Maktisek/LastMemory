# 📦 LAST MEMORY


> *Last Memory je textová hra vytvořená za účelem získání nových zkušeností v programování jako takovém.*


## 🌟 Zajímavé funkce hry

- Možnost si hru uložit a zase načíst
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

Tímto jsem posunul své zkušenosti na další level a naučil jsem se spoustu nových a skvěle využitelných dovedností⬆️

### ✍️ O autorovi

Jsem [Matěj Pospíšil](https://github.com/Maktisek) a nyní jsem ve druhém ročníku na střední elektrotechnické škole v Ječné📖 

Programuji v jazyce Java a v budoucnu budu programovat v jazycích C# a Python. 


## ⬇️ Instalační a spouštěcí proces
Ke spustění *Last Memory* je potřeba systém
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
Prvně bude třeba příkazový řádek "naučit" znakové kódování UTF-8. 
Tohle ale není problém, protože stačí do příkazové řádky napsat:
```cmd
chcp 65001
```
Tímto změníte znakové kódování na UTF-8📝

### ➡️ Nastavení cesty pro spuštění hry
Vzhledem k tomu, že příkazový řádek stále neví odkud budete *JAR* soubor spouštět, musíte mu to sdělit.

Nejprve si najděte hru v průzkumníku souborů. Zkontrolujete si, na jakém disku se soubor nachází.

Příkazový řádek říká, kde hledá. Pokud disk nesedí, napište název disku a dvojtečku.

Například chcete přepnout na disk D, pak napíšete:
```cmd
D:
```

Pokud disk sedí, není potřeba nic dělat❗

Nyní už jen nastavit cestu. Dejme tomu, že herní JAR soubor se nachází na: D:\Test.
Pak do příkazového řádku napište:
```cmd
cd \Test
```

Nyní příkazový řádek hledá v požadované složce, která obsahuje JAR soubor✅

### ▶️ Spuštění hry
Nyní poslední krok - spustit hru⚡

Do příkazové řádky napište:
```cmd
java -Dfile.encoding=UTF-8 -jar LastMemory.jar
```
Bez *-Dfile.encoding=UTF-8* by mohlo dojít k problémům při zadávání vstupů do herní konzole.

Pokud se JAR soubor jmenuje jinak, přepište _**java -Dfile.encoding=UTF-8 -jar LastMemory.jar**_ na _**java -Dfile.encoding=UTF-8 -jar JménoSouboru.jar**_

A je hotovo, hra běží🎉

## 💻 Jak hru ovládat
Hra s vámi komunikuje prostřednictvím textu. Vždy oznamuje, kde se nacházíte a co se právě děje.

Pokud chcete vykonat libovolnou akci, pak musíte napsat její příkazový klíč. Jak se ale dozvědět tento klíč?

### 🆘 Příkaz pomoc
Pokud napíšete příkaz pomoc, hra automaticky vypíše, co lze v daný moment dělat. 

Systém je to snadný:
"název příkazu" -> "upřesnění" = co se po zavolání provede. Vždy před druhým vstupem hra vypíše informaci o možnostech (některé příkazy mají pouze jeden vstup).

S touto radou se neztratíte🌟

## 📂 Systém uložení herního postupu
Hra nabízí možnost uložit si herní postup. Stačí k tomu nastavit mód na "nastavení" a zadat
příkaz "uložit". Hra neudává žádný limit k počtu savů. Lze jich mít klidně i 100!⬇️

Hra se pak uloží do speciální složky na vašem disku. Tedy přímo na _**user.home/LastMemorySaves**_ 🖥️

Mezi savy se dá během hraní hry přepínat🔄
Také je lze mazat🗑️

## 💻 Jak hru hrát
Po celou dobu gameplaye máte možnost zavolat příkaz "jak hrát". Ten detailně
popíše:
- jak se hra hraje✅
- co je cílem🎯
- nejlepší strategie🧠

### ❗Upozornění
Prosím, nezadávejte během hraní hry do konzole tyto vstupy:
- **Ctrl + Z** (Windows) / **Ctrl + D** (Linux/macOS) – ukončí vstup, Scanner přestane fungovat – hra se ukončí
- **Ctrl + C** – okamžitě ukončí hru (text ale kopírovat můžete)

Pokud jeden z těchto vstupů zadáte, hra se automaticky vypne❌

Pro normální ukončení použijte příkaz "opustit"✅

## 🔎 Závěr
Na hru *Last Memory* jsem velmi pyšný a sám bych nečekal, že uvidím tolik zlepšení.
Budu si vážit tvého času, který strávíš hraním mé hry.

Věřím, že v budoucnu stvořím ještě větší projekty✨
