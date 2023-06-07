using System;

namespace rt
{
    public class Sphere : Geometry
    {
        private Vector Center { get; set; }
        private double Radius { get; set; }

        public Sphere(Vector center, double radius, Material material, Color color) : base(material, color)
        {
            Center = center;
            Radius = radius;
        }

        public override Intersection GetIntersection(Line line, double minDist, double maxDist)
        {
            var x = line.X0 - Center;
            if (Math.Abs(x.X) > maxDist || Math.Abs(x.Y) > maxDist || Math.Abs(x.Z) > maxDist ||
                Math.Abs(x.X) < minDist || Math.Abs(x.Y) < minDist || Math.Abs(x.Z) < minDist)
            {
                return new Intersection();
            }
            
            var a = line.Dx * line.Dx;
            var b = 2 * (line.Dx * line.X0 - line.Dx * Center);
            var c = line.X0 * line.X0 + Center * Center - 2 * (line.X0 * Center) - Radius * Radius;
            
            var discriminant = b * b - 4 * a * c;
            
            if (discriminant < 0) return new Intersection();
            
            var smallestRoot = -b - Math.Sqrt(discriminant);

            return smallestRoot > 0 ? 
                new Intersection(true, true, this, line, smallestRoot / (2 * a)) :
                new Intersection();
        }

        public override Vector Normal(Vector v)
        {
            var n = v - Center;
            n.Normalize();
            return n;
        }
    }
}