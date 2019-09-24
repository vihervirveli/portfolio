using System;
using System.Collections.Generic;
using Jypeli;
using Jypeli.Assets;
using Jypeli.Controls;
using Jypeli.Effects;
using Jypeli.Widgets;

public class FrenchWord : PhysicsGame
{
    PhysicsObject pelaajanHead;
    PhysicsObject pelaajanVartalo;
    PhysicsStructure pelaaja;
    /*
     * Author: Maarit Salo ITKP102 Harjoitustyö 2011
     */ 
    public override void Begin()
    {
        Level.CreateBorders();
        pelaajanHead = PiirraPelattavatOsaset(this, 0, 0, 50.0, 50.0, Shape.Circle);
        pelaajanVartalo = PiirraPelattavatOsaset(this, 0, 0, 50.0, 100.0, Shape.Rectangle);
        pelaaja = new PhysicsStructure(pelaajanHead, pelaajanVartalo);
        pelaaja.Softness = 30;
        Add(pelaaja);

    }
    
    
    /// <summary>
    /// Aliohjelma joka piirtää peliin pelattavat osiot
    /// </summary>
    /// <param name="peli">tämä peli</param>
    /// <param name="x">x-koordinaatti</param>
    /// <param name="y">y-koordinaatti</param>
    /// <param name="r">säde</param>
    /// <param name="muoto">osasen muoto</param>
    public static PhysicsObject PiirraPelattavatOsaset(Game peli, double x, double y, double kanta, 
        double korkeus, Shape muoto)
     {
         PhysicsObject osa = new PhysicsObject(2 * kanta, 2 * korkeus, muoto);
         osa.Position = new Vector(x, y);
         peli.Add(osa);
         return osa;
         
     }
}
