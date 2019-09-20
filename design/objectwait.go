package main

func main(){
	p := make(chan int)
	go func() {
		for i := 1; i <= 100; i++ {
			p <- i
			if (i % 2 != 0) {
				println(i)
			}
		}
	}()

	go func() {
		for i := 1; i <= 100; i++ {
			<-p 
			if (i % 2 == 0) {
				println(i)
			}
		}
	}()

	sginal := make(chan int)
	<-sginal
}