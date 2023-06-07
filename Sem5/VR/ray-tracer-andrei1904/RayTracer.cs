using System;
using System.Diagnostics.CodeAnalysis;
using System.Linq;
using System.Runtime.InteropServices;

namespace rt
{
    class RayTracer
    {
        private Geometry[] geometries;
        private Light[] lights;

        public RayTracer(Geometry[] geometries, Light[] lights)
        {
            this.geometries = geometries;
            this.lights = lights;
        }

        private double ImageToViewPlane(int n, int imgSize, double viewPlaneSize)
        {
            var u = n * viewPlaneSize / imgSize;
            u -= viewPlaneSize / 2;
            return u;
        }

        private Intersection FindFirstIntersection(Line ray, double minDist, double maxDist)
        {
            var intersection = new Intersection();

            foreach (var geometry in geometries)
            {
                var intr = geometry.GetIntersection(ray, minDist, maxDist);

                if (!intr.Valid || !intr.Visible) continue;

                if (!intersection.Valid || !intersection.Visible)
                {
                    intersection = intr;
                }
                else if (intr.T < intersection.T)
                {
                    intersection = intr;
                }
            }

            return intersection;
        }

        private bool IsLit(Vector point, Light light)
        {
            var ray = new Line(light.Position, point);

            var intersection = FindFirstIntersection(ray, 0, (light.Position - point).Length());

            if (!intersection.Valid) return true;
            
            return (intersection.Position - point).Length() < 0.001;
        }

        public void Render(Camera camera, int width, int height, string filename)
        {
            var background = new Color();
            var image = new Image(width, height);
            
            for (var i = 0; i < width; i++)
            {
                for (var j = 0; j < height; j++)
                {
                    image.SetPixel(i, j, background);

                    var x1 = camera.Position + camera.Direction * camera.ViewPlaneDistance
                                             + (camera.Up ^ camera.Direction) *
                                             ImageToViewPlane(i, width, camera.ViewPlaneWidth)
                                             + camera.Up *
                                             ImageToViewPlane(j, height, camera.ViewPlaneHeight);

                    var ray = new Line(camera.Position, x1);
                    
                    var intersection = FindFirstIntersection(ray, camera.FrontPlaneDistance,
                        camera.BackPlaneDistance);

                    if (!intersection.Valid) continue;
                    
                    var color = new Color();
    
                    foreach (var light in lights)
                    {
                        var material = intersection.Geometry.Material;
                        var newColor = material.Ambient * light.Ambient;

                        if (IsLit(intersection.Position, light))
                        {
                            var L = light.Position;
                            var C = camera.Position;
                            var V = intersection.Position;
                            var E = (C - V).Normalize();
                            var N = intersection.Geometry.Normal(V);
                            var T = (L - V).Normalize();
                            var R = (N * (N * T) * 2 - T);
                            
                            if (N * T > 0)
                            {
                                newColor += material.Diffuse * light.Diffuse * (N * T);
                            }
                        
                            if (E * R > 0)
                            {
                                newColor += material.Specular * light.Specular *
                                            Math.Pow(E * R, material.Shininess);
                            }
                            
                            newColor *= light.Intensity;
                        }

                        color += newColor;
                    }

                    image.SetPixel(i, j, color);
                    
                }
            }

            image.Store(filename);

        }
    }
}