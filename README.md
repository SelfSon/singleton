Приветствую)

В классе **JobInterviewSingleton** я нашла момент, который я упустила при написании этого шаблона проэктирования

#### Момент, который был мной упущен:
1. DCL работает начиная с **Java 5** и только с использованием **volatile**
   
   Без volatile можно получить не полностью инициализированный объект, потому что создание нового объекта
   
   `instance = new JobInterviewSingleton();` - не атомарная операция
   
   Поскольку создание и инициализация объекта происходят в несколько шагов:
   1. local_ptr = malloc(sizeof(JobInterviewSingleton)) // выделение памяти под сам объект;
   2. JobInterviewSingleton::ctor(s); // конструирование объекта (инициализация полей);
   3. instance = local_ptr // инициализация указателя;

   Компилятор может поменять местами инструкции 2 и 3  для оптимизации.
   
   И пока в одном потоке *instance* будет инициализироваться, другой поток поток может получить не нулевую ссылку на 
   объект *instance* и начать использовать не до конца сконструированный объект

   **Решение**: объявить **instance** как **volatile**

В классе **LazySingleton** эти моменты были исправлены

Далее идут мои заметки о реализации паттерна **Singleton с ленивой инициализацией для многопоточной среды** и использованием 
**DCL (Double-Checked Locking) Concurrency Design Pattern**

### Singleton creational design pattern:
- гарантирует, что экземпляр объекта будет создан только 1 раз
- предоставляет глобальную точку доступа для этого же экземпляра

### Double-Checked Locking concurrency design pattern:
- полезен, когда критическая секция должна быть выполнена только 1 раз
- полезен, когда несколько потоков одновременно могут попытаться выполнить критическую секцию
- для избежания блокировок и накладных расходов на синхронизацию, когда объект уже инициализирован

### Реализация:
- перед блокировкой для синхронизации проверить еще раз условие блокировки

### DCL benefit:
- повышение производительности

### volatile:
- гарантирует, что все записи и чтения переменной происходят из главной памяти
- запретит компилятору менять порядок инструкций местами

### Заметки:
1. DCL не работает до Java 5 даже с использованием volatile
   (так как компилятор мог поменять инструкции местами, даже для volatile переменных)

Материалы, которые мне помогли:

https://www.dre.vanderbilt.edu/~schmidt/PDF/DC-Locking.pdf
https://www.cs.umd.edu/~pugh/java/memoryModel/DoubleCheckedLocking.html
https://www.cs.cornell.edu/courses/cs6120/2019fa/blog/double-checked-locking/
https://gee.cs.oswego.edu/dl/cpj/jmm.html

Если у Вас есть какие-то дополнения, я буду рада их услышать
