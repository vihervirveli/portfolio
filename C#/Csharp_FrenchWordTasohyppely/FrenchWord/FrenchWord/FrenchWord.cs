using System;
using System.Collections.Generic;
using Jypeli;
using Jypeli.Assets;
using Jypeli.Controls;
using Jypeli.Effects;
using Jypeli.Widgets;
using System.Linq;
using System.Text;
using System.Diagnostics;

/// @author Maarit Salo
/// @version 1.12.2011
/// <summary>
/// ITKP102 Harjoitustyö 2011 - French Word
/// </summary>
public class FrenchWord : PhysicsGame
{
    private PlatformCharacter pelaaja;
    private IntMeter pelaajanPisteet = new IntMeter(0);
    private IntMeter pelaajanVirheet = new IntMeter(3);
    private int tasoMissaOllaan = 0;
    private int oikeankuvanIndeksi = 0;
    private int vaarankuvanIndeksi = 0;
    private Widget laskuriLaatta;
    private string[] kenttaSanat = { "LUNE", "JAUNE", "PEUR", "LIMACE" };
    private string[] kenttaLauseet = { "*the Moon*. Hint: La _____", "*Yellow*.", "*Fear*. Hint: La _____", "*Snail*. Hint: La ______" };
    private static readonly String[] taso0 = {
                  "                        ",
                  "                        ",
                  "                        ",
                  "  XeX              XsX  ",
                  "   X       X X      X   ",
                  "            X           ",
                  "     R               R  ", 
                  "                        ",
                  "  XeXX            XXXeX ",
                  " X        XXXXX       X ",
                  "                        ",
                  "                        ",
                  "                        ",
                  "          o          X  ",
                  "          X             ",
                  "  XXXX            XXXXX ",
                  "                        ",
                  "                        ",
                  "               X        ",
                  "                        ", 
                  " s          X        Re ", 
                  "XX        XXXXX       XX", 
                  "XXXX     XXXXXXX    XXXX",
                  "XXXXXXXXXXXXXXXXXXXXXXXX",
                  };

    private static readonly String[] taso1 = {
                  "                        ",
                  "                        ",
                  "       e                ",
                  "            R           ",
                  "  s                     ",
                  " XX        XXX       XX ",
                  "                        ", 
                  " e                    e ",
                  "                        ",
                  "     XX         XX      ",
                  "                        ",
                  "                        ",
                  "           o          s ",
                  " XX       XXX        XX ",
                  "                        ",
                  "                        ",
                  " s   XX         XX    e ",
                  "                        ",
                  "                        ",
                  " XX         XX       XX ", 
                  "                        ", 
                  " s   R              Re  ", 
                  "                        ",
                  "XXXXXXXXXXXXXXXXXXXXXXXX",
                  };

    private static readonly String[] taso2 = {
                  "                        ",
                  "                        ",
                  "                        ",
                  "    e                   ",
                  "   XXXX               e ",
                  " s XXXXXXXXX          X ",
                  "       XXXXXXXXXX       ", 
                  "                      s ",
                  "                        ",
                  "                    R   ",
                  "                        ",
                  "             XXXXXXXXXX ",
                  "     R              s   ",
                  "                        ",
                  "  s        o            ",
                  "  XXXXXXXXXX            ",
                  "  s                  XX ",
                  " e     R         R      ",
                  "                        ",
                  " XXXX  XX       XXXX    ", 
                  "                        ", 
                  "                 R    e ", 
                  "                        ",
                  "XXXXXXXXXXXXXXXXXXXXXXXX",
                 };

    private static readonly String[] taso3 = {
                  "                        ",
                  "                        ",
                  "                        ",
                  "  XsX              XeX  ",
                  "   X       X X      X   ",
                  "            X           ",
                  "     R             R    ", 
                  "                        ",
                  "  XeXX             XXsX ",
                  "    X      XeX      X   ",
                  "            X           ",
                  "     X         X        ",
                  "          o          Xe ",
                  "          X             ",
                  "                        ",
                  "                        ",
                  " X X              XeX   ",
                  "  X        X X     X    ",
                  "            X           ",
                  "                        ", 
                  "                        ", 
                  " e R        X      R    ", 
                  "                      s ",
                  "XXXXXXXXXXXXXXXXXXXXXXXX",
                  };

    private static readonly Image[] kenttaTaustakuvat = Game.LoadImages("kenttarustausta", "kenttasintausta", "kenttavihtausta");
    private static readonly string[][] tasotaulukko = { taso0, taso1, taso2, taso3 };
    //lune
    private static readonly Image[] oikeatKuvat0 = Game.LoadImages("kentta0n", "kentta0l", "kentta0e", "kentta0u");
    // a, i
    private static readonly Image[] vaaratKuvat0 = Game.LoadImages("kentta0a", "kentta0i");

    //jaune
    private static readonly Image[] oikeatKuvat1 =
        Game.LoadImages("kentta1e", "kentta1n", "kentta1u", "kentta1a", "kentta1j");
    //gris
    private static readonly Image[] vaaratKuvat1 =
        Game.LoadImages("kentta1s", "kentta1i", "kentta1r", "kentta1g");

    //peur
    private static readonly Image[] oikeatKuvat2 =
        Game.LoadImages("kentta2r", "kentta2u", "kentta2e", "kentta2p");
    //angois
    private static readonly Image[] vaaratKuvat2 =
        Game.LoadImages("kentta2s", "kentta2i", "kentta2o", "kentta2g", "kentta2n", "kentta2a");

    //limace
    private static readonly Image[] oikeatKuvat3 =
        Game.LoadImages("kentta3l", "kentta3i", "kentta3m", "kentta3a", "kentta3e", "kentta3c");
    //rgo (sekottaa escargot'n jos ei lue vinkkiä)
    private static readonly Image[] vaaratKuvat3 =
        Game.LoadImages("kentta3r", "kentta3g", "kentta3o");
    private StringBuilder pelaajanNimijono = new StringBuilder();
    private Image[][] kenttienOikeatKerattavat = { oikeatKuvat0, oikeatKuvat1, oikeatKuvat2, oikeatKuvat3 };
    private Image[][] kenttienVaaratKerattavat = { vaaratKuvat0, vaaratKuvat1, vaaratKuvat2, vaaratKuvat3 };
    private List<PhysicsObject> apuopeLista = new List<PhysicsObject>();
    private List<PhysicsObject> kerattavaKamaLista = new List<PhysicsObject>();
    private const double nopeusVasemmalle = -200;
    private const double nopeusOikealle = 200;
    private int tileWidth;
    private int tileHeight;


    /// <summary>
    /// Varmistetaan että kenttä ottaa tietonsa ruudun koosta ja laitetaan uusi peli käyntiin
    /// </summary>
    public override void Begin()
    {
        tileWidth = (int)Screen.Width / taso0[0].Length;
        tileHeight = (int)Screen.Height / taso0.Length;
        AloitaUusiPeli();
    }


    /// <summary>
    /// Aliohjelma joka aloittaa uuden pelin kertomalla pelaajalle pelin idean ja kysymällä pelaajan nimen
    /// </summary>
    public void AloitaUusiPeli()
    {
        Level.Background.Image = null;
        Level.BackgroundColor = Color.Black;
        Shuffle(kenttaTaustakuvat);
        Level.BackgroundColor = Color.Black;
        InputWindow kysymysIkkuna = new InputWindow("The purpose of the game is to collect the French words whose English equivalents are indicated to the player before the level starts. If you accidentally take the wrong letter or run into a subteacher you will get a strike. You're allowed three strikes before the level gets reset. Give your name to move forward. Good luck!");
        kysymysIkkuna.TextEntered += ProcessInput;
        IsMouseVisible = true;
        kysymysIkkuna.IsModal = true;
        Add(kysymysIkkuna);
    }


    /// <summary>
    /// Aliohjelma joka pistää pelaajan nimen muistiin ja vie pelaajan kenttiin
    /// </summary>
    /// <param name="pelaajanNimi">teksti mitä pelaaja antoi</param>
    public void ProcessInput(InputWindow pelaajanNimi)
    {
        string vastaus = pelaajanNimi.InputBox.Text;
        pelaajanNimijono.Append(vastaus);
        LisaaLaskurit();
        KenttaValiStop(0);
    }


    /// <summary>
    /// Aliohjelma joka kertoo seuraavassa kentässä tarvittavan sanan, 
    /// tai vaihtoehtoisesti siirtää pelaajan onniteltavaksi
    /// </summary>
    /// <param name="arvo">0, jotta aliohjelmaa voi kutsua multiselectWindowsta</param>
    public void KenttaValiStop(int arvo)
    {
        if (tasoMissaOllaan == tasotaulukko.Length)
        {
            Onnittelu();
            return;
        }
        Resetointi();
        LaskurinNakyvyys(false);
        Level.Background.Image = null;
        Level.BackgroundColor = Color.Black;
        MultiSelectWindow ohjeikkuna =
        new MultiSelectWindow("Collect the letters you need to write in French "
                    + kenttaLauseet[tasoMissaOllaan], "OK");
        ohjeikkuna.ItemSelected += new Action<int>(KenttaSiirtyma);
        IsMouseVisible = true;
        Camera.Zoom(2);
        Add(ohjeikkuna);
    }


    /// <summary>
    /// Aliohjelma joka hoitelee kentästä toiseen siirtymisen
    /// </summary>
    /// <param name="arvo">0, jotta aliohjelmaa voi kutsua multiselectWindowsta</param>
    void KenttaSiirtyma(int arvo)
    {
        if (tasoMissaOllaan < tasotaulukko.Length) LuoKentat(0);
        else Onnittelu();
    }


    /// <summary>
    /// Aliohjelma joka pistää pystyyn kentät
    /// </summary>
    /// <param name="arvo">0, jotta aliohjelmaa voi kutsua multiselectWindowsta</param>
    void LuoKentat(int arvo)
    {
        TileMap tiles = TileMap.FromStringArray(tasotaulukko[tasoMissaOllaan]);
        int kentanLeveys = taso0[0].Length;
        int kentanKorkeus = taso0.Length;
        string[] tasonKuva = tasotaulukko[tasoMissaOllaan];
        tiles['X'] = PiirraPelinOsatYleensa;
        tiles['o'] = LuoPelaaja;
        tiles['e'] = delegate { return LuoKerattavaKama(kenttienOikeatKerattavat, ref oikeankuvanIndeksi, "oikea"); };
        tiles['s'] = delegate { return LuoKerattavaKama(kenttienVaaratKerattavat, ref vaarankuvanIndeksi, "vaara"); };
        tiles['R'] = LuoApuope;
        tiles.Insert(tileWidth, tileHeight);
        Gravity = new Vector(0, -400);
        Camera.ZoomToLevel();
        LaskurinNakyvyys(true);
        if (tasoMissaOllaan == 0) Level.Background.CreateGradient(Color.White, Color.DarkCyan);
        else Level.Background.Image = kenttaTaustakuvat[tasoMissaOllaan - 1];
        Level.Background.FitToLevel();
        AsetaOhjaimet();
    }


    /// <summary>
    /// Aliohjelma joka joko näyttää tai piilottaa laskurin
    /// </summary>
    /// <param name="nakyviinVaiEi">jos true, nakyviin, jos false piiloon</param>
    public void LaskurinNakyvyys(bool nakyviinVaiEi)
    {
        foreach (Widget w in laskuriLaatta.Objects)
        {
            w.Visible = nakyviinVaiEi;
        }
    }


    /// <summary>
    /// Sotkee rakenteen satunnaiseen järjestykseen.
    /// Lainattu Jypelistä.
    /// </summary>
    /// <typeparam name="T">Minkä tyyppisiä alkioita sotketaan</typeparam>
    /// <param name="list">tietorakenne jossa sotkettavat alkiot</param>
    public static void Shuffle<T>(IList<T> list)
    {
        Random rand = new Random();
        for (int i = list.Count; i > 1; i--)
        {
            // Pick random element to swap.
            int j = rand.Next(i); // 0 <= j <= i-1
            // Swap.
            T tmp = list[j];
            list[j] = list[i - 1];
            list[i - 1] = tmp;
        }
    }


    /// <summary>
    /// Aliohjelma jossa resetoidaan edellisen kentän arvot ja laskurit takaisin oletuksiksi
    /// </summary>
    void Resetointi()
    {
        ClearGameObjects();
        ClearControls();

        apuopeLista.Clear();
        oikeankuvanIndeksi = 0;
        vaarankuvanIndeksi = 0;

        pelaajanPisteet.Reset();
        pelaajanVirheet.Reset();
    }


    /// <summary>
    /// Aliohjelma joka lisää pelaajan piste- ja elämälaskurit ruutuun.
    /// </summary>
    void LisaaLaskurit()
    {
        laskuriLaatta = new Widget(new VerticalLayout());
        pelaajanPisteet = LuoPisteLaskuri(Screen.Right - 150.0, Screen.Top - 50.0, 0, "Player's points: ");
        pelaajanVirheet = LuoPisteLaskuri(Screen.Right - 150.0, Screen.Top - 70.0, 3, "Player's errors: ");

    }


    /// <summary>
    /// Aliohjelma joka luo pistelaskurin
    /// </summary>
    /// <param name="x">pistelaskurin x-koordinaatti</param>
    /// <param name="y">pistelaskurin y-koordinaatti</param>
    /// <param name="lahtoarvo">mikä on pelin alkaessa laskurin arvo</param>
    /// <param name="otsikko">laskurin selite sille, mitä lasketaan</param>
    /// <returns>laskurin</returns>
    IntMeter LuoPisteLaskuri(double x, double y, int lahtoarvo, string otsikko)
    {
        IntMeter laskuri = new IntMeter(lahtoarvo);
        Label naytto = new Label();
        naytto.BindTo(laskuri);
        naytto.X = x;
        naytto.Y = y;
        naytto.TextColor = Color.Black;
        naytto.BorderColor = Color.DarkGray;
        naytto.Color = Color.DarkGray;
        Add(naytto);
        Label pisteTeksti = new Label(otsikko);
        pisteTeksti.X = x - 100;
        pisteTeksti.Y = y;
        pisteTeksti.TextColor = Color.Black;

        Add(pisteTeksti);
        laskuriLaatta.Add(naytto);
        laskuriLaatta.Add(pisteTeksti);
        return laskuri;
    }


    /// <summary>
    /// Aliohjelma joka piirtää kerättävät esineet = kamat
    /// </summary>
    /// <param name="kuvat">kuvataulukkotaulukko</param>
    /// <param name="kuvienIndeksi">indeksi missä kohtaa kuvataulukossa mennään</param>
    /// <param name="tunniste">kaman tunniste</param>
    /// <returns>kerättävät esineet</returns>
    public PhysicsObject LuoKerattavaKama(Image[][] kuvat, ref int kuvienIndeksi, string tunniste)
    {
        PhysicsObject kama = PhysicsObject.CreateStaticObject
            (tileWidth * 0.5, tileHeight * 0.7,
            Shape.Rectangle);
        kama.Tag = tunniste;
        kama.Color = Color.DarkViolet;
        kama.Image = kuvat[tasoMissaOllaan][kuvienIndeksi];
        kerattavaKamaLista.Add(kama);
        if (kuvienIndeksi + 1 < kuvat[tasoMissaOllaan].Length)
            kuvienIndeksi++;
        return kama;
    }


    /// <summary>
    /// Ohjaimet paikalleen
    /// </summary>
    void AsetaOhjaimet()
    {
        Keyboard.Listen(Key.Escape, ButtonState.Pressed, Exit, "Poistu");
        Keyboard.Listen(Key.F1, ButtonState.Pressed, ShowControlHelp, "Näytä näppäinohjeet");
        Keyboard.Listen(Key.Up, ButtonState.Pressed, Hyppy, "Pelaaja hyppää", pelaaja);
        Keyboard.Listen(Key.Up, ButtonState.Released, AsetaNopeus, null, pelaaja, Vector.Zero);
        Keyboard.Listen(Key.Left, ButtonState.Down, Kavely, "Pelaaja kävelee vasemmalle", pelaaja, nopeusVasemmalle);
        Keyboard.Listen(Key.Left, ButtonState.Released, AsetaNopeus, null, pelaaja, Vector.Zero);
        Keyboard.Listen(Key.Right, ButtonState.Down, Kavely, "Pelaaja kävelee oikealle", pelaaja, nopeusOikealle);
        Keyboard.Listen(Key.Right, ButtonState.Released, AsetaNopeus, null, pelaaja, Vector.Zero);

    }


    /// <summary>
    /// Tasohyppelijä kävelee
    /// </summary>
    /// <param name="pelaaja">tasohyppelijä</param>
    /// <param name="nopeus">sysäys jolla th halutaan hyppäävän</param>
    void Kavely(PlatformCharacter pelaaja, double nopeus)
    {
        pelaaja.Walk(nopeus);
    }


    /// <summary>
    /// Tasohyppelijä hyppää
    /// </summary>
    /// <param name="pelaaja">tasohyppelijä</param>
    /// <param name="nopeus">nopeus jolla th halutaan kävelevän</param>
    void Hyppy(PlatformCharacter pelaaja)
    {
        pelaaja.Jump(1400.0);
    }


    /// <summary>
    /// Asettaa nopeuden oliolle
    /// </summary>
    /// <param name="olio">olio</param>
    /// <param name="nopeus">nopeus</param>
    void AsetaNopeus(PhysicsObject olio, Vector nopeus)
    {
        olio.Velocity = nopeus;
    }


    /// <summary>
    /// Aliohjelma joka piirtää pelattaviin kenttiin (miinus peruskenttä) rakenteet
    /// </summary>
    /// <returns>yksittäisen rakenteen</returns>
    public PhysicsObject PiirraPelinOsatYleensa()
    {
        PhysicsObject osa = PhysicsObject.CreateStaticObject(tileWidth, tileHeight,
            Shape.Rectangle);
        osa.Color = Color.DarkGreen;
        //osa.Image = Game.LoadImage("rakennekuva");
        return osa;
    }


    /// <summary>
    /// Aliohjelma joka luo pelaajan
    /// </summary>
    /// <returns>pelattavan hahmon = pelaajan</returns>
    public PlatformCharacter LuoPelaaja()
    {
        pelaaja = new PlatformCharacter(tileWidth * 0.5, tileHeight * 1.5, Shape.Rectangle);
        pelaaja.Tag = "pelaaja";
        pelaaja.Image = Game.LoadImage("pelaaja");
        AddCollisionHandler(pelaaja, "oikea", KirjainPoimittu);
        AddCollisionHandler(pelaaja, "vaara", VirheTeko);
        AddCollisionHandler(pelaaja, "apuope", VirheTeko);
        return pelaaja;
    }


    /// <summary>
    /// Aliohjelma joka käsittelee pelaajan virheet (väärät kirjaimet ja apuopettajan
    /// kanssa törmäämiset): vähentää pelaajalle sallittuja virheitä yhdellä
    /// </summary>
    /// <param name="pelaaja">pelaaja joka törmää</param>
    /// <param name="virheliike">väärä törmäämisen kohde (joka promptaa virheen)</param>
    void VirheTeko(IPhysicsObject pelaaja, IPhysicsObject virheliike)
    {
        pelaajanVirheet.Value--;
        if (pelaajanVirheet.Value == 0)
        {
            Resetointi();
            LaskurinNakyvyys(true);
            LuoKentat(0);
        }
    }


    /// <summary>
    /// Aliohjelma joka käsittelee pelaajan törmäyksen oikeat kuvat sisältävän
    /// fysiikkaolion kanssa: kasvattaa pelaajan pisteitä yhdellä
    /// </summary>
    /// <param name="pelaaja">pelaaja joka törmää</param>
    /// <param name="kirjain">kirjain johon pelaaja törmää</param>
    void KirjainPoimittu(IPhysicsObject pelaaja, IPhysicsObject kirjain)
    {
        pelaajanPisteet.Value++;
        kirjain.Destroy();
        if (pelaajanPisteet.Value == kenttienOikeatKerattavat[tasoMissaOllaan].Length)
        {
            MultiSelectWindow valintaIkkuna =
                new MultiSelectWindow("Correct! The word was indeed "
                    + kenttaSanat[tasoMissaOllaan] + ". Click OK to move forward", "OK");
            valintaIkkuna.ItemSelected += new Action<int>(KenttaValiStop);

            IsMouseVisible = true;
            Add(valintaIkkuna);
            foreach (PhysicsObject apuope in apuopeLista)
            {
                Explosion rajahdys = new Explosion(300);
                rajahdys.Position = apuope.Position;
                Add(rajahdys);
                apuope.Destroy();
            }

            foreach (PhysicsObject kerattavaKama in kerattavaKamaLista)
            {
                Explosion rajahdys = new Explosion(50);
                rajahdys.Position = kerattavaKama.Position;
                Add(rajahdys);
                kerattavaKama.Destroy();
            }
            tasoMissaOllaan++;
        }
    }


    /// <summary>
    /// Aliohjelma joka onnittelee pelaajaa kaikkien kenttien läpäisemistä sen nimellä, ja antaa valinnan
    /// joko uudelleen aloittamisesta tai pelistä poistumisesta
    /// </summary>
    void Onnittelu()
    {
        if (pelaajanNimijono.Length == 0) pelaajanNimijono.Append("The player");
        MultiSelectWindow valintaIkkuna = new MultiSelectWindow("Congratulations! " + pelaajanNimijono.ToString() + " has completed all levels!", "Restart", "Quit");
        valintaIkkuna.ItemSelected += new Action<int>(VikaKenttaValinta);
        // valintaIkkuna.Buttons = new PushButton[] { Key.Enter, Key.Escape };
        Add(valintaIkkuna);
    }


    /// <summary>
    /// Aliohjelma joka joko aloittaa pelin alusta tai poistuu pelistä
    /// </summary>
    /// <param name="arvo">pelaajan päätös (0 = peli alusta, 1 = poistutaan pelistä)</param>
    void VikaKenttaValinta(int arvo)
    {
        if (arvo == 0)
        {
            tasoMissaOllaan = 0;
            Resetointi();
            LaskurinNakyvyys(false);
            pelaajanNimijono.Clear();
            AloitaUusiPeli();

        }

        else Exit();
    }


    /// <summary>
    /// Aliohjelma joka palauttaa apuopettajan ja lisää sen listaan
    /// </summary>
    /// <returns>apuopettaja</returns>
    public PhysicsObject LuoApuope()
    {
        double apuopenLeveys = 0.5 * tileWidth;
        double apuopenKorkeus = 1.5 * tileHeight;

        PhysicsObject apuope = new PhysicsObject(apuopenLeveys, apuopenKorkeus, Shape.Rectangle);
        apuope.Tag = "apuope";
        apuope.CanRotate = false;
        //apuope.Color = Color.DarkOrange;
        apuope.Image = Game.LoadImage("apuope");
        apuopeLista.Add(apuope);
        TagFollowerBrain seuraajanAivot = new TagFollowerBrain("pelaaja", 4500);
        apuope.Brain = seuraajanAivot;
        return apuope;
    }
}
