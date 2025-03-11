import turtle

screen = turtle.Screen()
screen.bgcolor("white")
pen = turtle.Turtle()
pen.speed(0)

# square
def draw_square(color, size):
    pen.color(color)
    pen.begin_fill()
    for _ in range(4):
        pen.forward(size)
        pen.right(90)
    pen.end_fill()

# circle
def draw_circle(color, radius):
    pen.color(color)
    pen.begin_fill()
    pen.circle(radius)
    pen.end_fill()

# \red circle
pen.penup()
pen.goto(0, -200)
pen.pendown()
draw_circle("red", 200)

# black diamond
pen.penup()
pen.goto(0, 200)
pen.setheading(-45)
pen.pendown()
draw_square("black", 282)

# blue square
pen.penup()
pen.goto(-100, 100)
pen.setheading(0)
pen.pendown()
draw_square("blue", 199)

pen.hideturtle()
screen.mainloop()
