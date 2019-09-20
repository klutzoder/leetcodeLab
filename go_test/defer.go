package main

import (
	"fmt"
	"runtime"	
	"time"
	"sync/atomic"
)

func main() {
	var x int32
	threads := runtime.GOMAXPROCS(0) - 1
	for i := 0; i < threads; i++ {
		go func() {
			for { 
				atomic.AddInt32(&x, 1)
			}
		}()
	}
	time.Sleep(time.Second)
	fmt.Println("x =", x)
}