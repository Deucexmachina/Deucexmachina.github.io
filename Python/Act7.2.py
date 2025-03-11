import turtle

screen = turtle.Screen()
screen.bgcolor("white")
    
pen = turtle.Turtle()
pen.speed(0)
pen.width(10)

# blue circle
pen.penup()
pen.goto(0, -100)
pen.pendown()
pen.pensize(6)
pen.color("black", "blue")
pen.begin_fill()
pen.circle(100)
pen.end_fill()
    
# white circle
pen.penup()
pen.goto(0, -80)
pen.pendown()
pen.color("black", "white")
pen.begin_fill()
pen.circle(80)
pen.end_fill()

# blue line
pen.penup()
pen.goto(-65, 65)
pen.setheading(-45)
pen.pensize(15)
pen.color("blue")
pen.pendown()
pen.forward(180)
pen.penup()

pen.hideturtle()
screen.mainloop()