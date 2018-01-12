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
                stopwatch.Restart();
                try
                {
                    result = client.DownloadString($"http://roboto.elyspioweb.xyz/assets/data/speed.txt");
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
