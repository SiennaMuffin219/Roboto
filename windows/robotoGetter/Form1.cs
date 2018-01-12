using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Net;

namespace AlphaROBOTO
{
    public partial class Form1 : Form
    {
        private bool IsMouseDown = false;
        private bool debug = true;
        private WebClient client = null;


        public Form1()
        {
            InitializeComponent();
            client = new WebClient();
        }
        
        private void Form1_Paint(object sender, PaintEventArgs e)
        {
            Pen pen = new Pen(Color.Black);
            e.Graphics.DrawLine(pen, 100, 0, 100, 200);
            e.Graphics.DrawLine(pen, 120, 0, 120, 200);
        }

        private void Form1_MouseDown(object sender, MouseEventArgs e)
        {
            IsMouseDown = true;
            Form1_MouseMove(sender, e);
        }

        private void Form1_MouseUp(object sender, MouseEventArgs e)
        {
            IsMouseDown = false;
        }

        private void Form1_MouseMove(object sender, MouseEventArgs e)
        {
            int x = e.X, y = e.Y;
            if (IsMouseDown && x > 0 && x < 200 && y > 0 && y < 200)
            {
                int SpeedL = 0, SpeedR = 0;

                if (debug)
                {
                    //label1.Text = $"{x} - {y}";
                    int speed = -(y - 100) * 10;
                    label1.Text = speed.ToString();
                    System.Threading.Thread.Sleep(200);
                }

                if (x >= 100 && x <= 120)
                {
                    //TODO Forward
                    SpeedL = SpeedR = -(y - 100) * 10;
                    client.DownloadString($"http://roboto.elyspioweb.xyz/assets/php/android.php?speedL={SpeedL}&speedR={SpeedR}");
                }
                else
                {
                    if (x < 100)
                    {
                        SpeedL = (-(y - 100) * 10) * x / 100;
                        SpeedR = -(y - 100) * 10;
                        client.DownloadString($"http://roboto.elyspioweb.xyz/assets/php/android.php?speedL={SpeedL}&speedR={SpeedR}");
                    }
                    if (x > 120)
                    {
                        SpeedL = -(y - 100) * 10;
                        SpeedR = (-(y - 100) * 10) * -(x - 220) / 100;
                        client.DownloadString($"http://roboto.elyspioweb.xyz/assets/php/android.php?speedL={SpeedL}&speedR={SpeedR}");
                    }
                }
            }
        }


    }
}
