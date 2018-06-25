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
            e.Graphics.DrawLine(pen, 0, 100, 220, 100);
        }

        private void Form1_MouseDown(object sender, MouseEventArgs e)
        {
            IsMouseDown = !IsMouseDown;
            Form1_MouseMove(sender, e);
        }

        private void Form1_MouseMove(object sender, MouseEventArgs e)
        {
            int x = e.X, y = e.Y;
            if (IsMouseDown && x > 0 && x < 200 && y > 0 && y < 200)
            {
                int SpeedL = 0, SpeedR = 0;

                if (debug)
                {
                    int speed = -(y - 100) * 10;
                    System.Threading.Thread.Sleep(200);
                }

                if (x >= 100 && x <= 120)
                {
                    SpeedL = SpeedR = -(y - 100) * 10;
                    Send(SpeedL, SpeedR);
                }
                else
                {
                    if (x < 100)
                    {
                        SpeedL = (-(y - 100) * 10) * x / 100;
                        SpeedR = -(y - 100) * 10;
                        Send(SpeedL, SpeedR);
                    }
                    if (x > 120)
                    {
                        SpeedL = -(y - 100) * 10;
                        SpeedR = (-(y - 100) * 10) * -(x - 220) / 100;
                        Send(SpeedL, SpeedR);
                    }
                }
            }
        }

        private void Send(int SpeedL, int SpeedR)
        {
            //if (client.DownloadStringTaskAsync($"http://roboto.elyspioweb.xyz/assets/php/android.php?speedL={SpeedL}&speedR={SpeedR}").Wait(100))
            //{
            //    label1.Text = "424242424242424242424242424242";
            //    System.Threading.Thread.Sleep(55555550);
            //}
            client.DownloadString($"http://roboto.elyspioweb.xyz/assets/php/android.php?speedL={SpeedL}&speedR={SpeedR}");
            label1.Text = $"{SpeedL} | {SpeedR}";
        }
    }
}
