function CubicParametricHermite(p,q,m,n)
    syms t PX(t) PF(t);
    no=100;
    PX(t)=p(1)*(2*t^3-3*t^2+1)+n(1)*(t^3-t^2)+m(1)*(t^3-2*t^2+t)+q(1)*(3*t^2-2*t^3);
    PF(t)=p(2)*(2*t^3-3*t^2+1)+n(2)*(t^3-t^2)+m(2)*(t^3-2*t^2+t)+q(2)*(3*t^2-2*t^3);
    tspace=linspace(0,1,no);
    points=zeros(6,no);
    for i=1:no
        points(1,i)=PX(tspace(i));
        points(2,i)=PF(tspace(i));
        points(3,i)=n(1)+(q(1)-n(1))*tspace(i);
        points(4,i)=n(2)+(q(2)-n(2))*tspace(i);
        points(5,i)=p(1)+(m(1)-p(1))*tspace(i);
        points(6,i)=p(2)+(m(2)-p(2))*tspace(i);
    end
    plot(points(1,:),points(2,:),'b');
    hold on;
    plot(points(3,:),points(4,:),'--r');
    hold on;
    plot(points(5,:),points(6,:),'--r');
    hold on;
    hold off;
end
