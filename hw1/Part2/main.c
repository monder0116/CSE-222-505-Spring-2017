#include "stdio.h"
#include "stdlib.h"
#include "string.h"
/* Bu programda C dili ile encapsulation
	 implement edilmiştir.
*/


/*
	Person isimli structure oluşturup data type
	olarak belirsiz tip olan void *'ı kullanıldı.
	Bunun nedeni dışarıdan direct olarak veri 
	üzerinde bir değişiklik yapılmamasını sağlaması oldu.
*/

typedef struct 
{
	void* name;
	void* age;
}Person;

/* Name'de tutulan bilgiyi down cast yaparak return eder.
	Ve buradaki casting sonucunu const olarak return edilmesini
	sağlar.*/
const char* getName(Person *obj) {
	char* temp=(char*)obj->name;
	return temp;

}
/* Age'de tutulan bilgiyi down cast yaparak return eder.
	Ve buradaki casting sonucunu return edilmesini
	sağlar.*/
 int getAge(Person *obj) {
 	int *temp=obj->age;
	return *temp;
}
/* Name değerinin değerinin değişmesi için eğer ilk set ise
 malloc ile yer alır. Daha sonra setler için bu alınan yeri
 verir tekrar gerekli yeni string boyutu kadar yer alır.
  ve değişimi sağlanır.Doğrudan bilgiye erişip değiştiremedikleri
 için bu fonksiyon kullanılması gerekir*/
void setName(Person *obj,const char* newname){
	if(obj->name!=NULL)
		free(obj->name);
	obj->name=malloc(sizeof(char)*strlen(newname)+1);
	char *temp=obj->name;
	strcpy(obj->name,newname);
	
}
/* Age değerinin değerinin değişmesi için eğer ilk set ise
 malloc ile yer alır. Daha sonra setler için bu değeri sadece
 değişimi sağlanır. Doğrudan bilgiye erişip değiştiremedikleri
 için bu fonksiyon kullanılması gerekir*/
void setAge(Person *obj,int newage){
	if(obj->age==NULL)
		obj->age=malloc(sizeof(int));
	int *temp=(int*)obj->age;
	(*temp)=newage;

}
/* Program biteceği zaman alınan hafızaları geri
 iade eder*/

void killPerson(Person *user){
	if(user->age!=NULL)
		free(user->age);
	if(user->name!=NULL)
		free(user->name);
	if(user!=NULL)
		free(user);
}

int main(){
	Person *user1=malloc(sizeof(Person));
	user1->name=NULL;
	user1->age=NULL;
	setAge(user1,12);
	setName(user1,"mehmet");
	printf("%d \n",getAge(user1));
	printf("%s \n",getName(user1));
	killPerson(user1);

	return 0;
}
