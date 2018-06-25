using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Net;
using System.Diagnostics;
using System.Threading.Tasks;

namespace RobotoReader
{
    class Program
    {
        static void Main(string[] args)
        {
            var client = new WebClient();
            Stopwatch stopwatch = new Stopwatch();
            string result = "";
            while (true)
            {
                Task<string> task = null;
                stopwatch.Restart();
                try
                {
                    task = client.DownloadStringTaskAsync($"http://roboto.elyspioweb.xyz/assets/data/speed.txt");
                    while (!task.Wait(40))
                    {
                        task = client.DownloadStringTaskAsync($"http://roboto.elyspioweb.xyz/assets/data/speed.txt");
                        Console.WriteLine("iyhuojipfejimhgmfdjokiulhfdskiohefrsulimsjqjsziheufrlmjdiehfruiljfdhflhbhedoufrljnmjidheliufrhbj");
                    }
                    result = task.Result;
                } catch { }
                stopwatch.Stop();
                Console.Clear();
                Console.WriteLine(result);
                Console.WriteLine(stopwatch.ElapsedMilliseconds);
                System.Threading.Thread.Sleep(200);
            }
        }
    }
}
