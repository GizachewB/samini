package com.example.samini.config;

import com.example.samini.domain.Student;
import com.example.samini.processor.StudentProcessor;
import com.example.samini.repository.StudentRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public StudentRepository studentRepository;

    @Bean
    public FlatFileItemReader<Student> reader() {
        return new FlatFileItemReaderBuilder<Student>()
                .name("studentItemReader")
                .resource(new ClassPathResource("data.csv"))
                .delimited()
                .names(new String[]{"firstName", "lastName","gpa","age"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
                    setTargetType(Student.class);
                }})
                .build();
    }

    @Bean
    public ItemWriter<Student> writer() {
        RepositoryItemWriter<Student> writer = new RepositoryItemWriter<>();
        writer.setRepository(studentRepository);
        writer.setMethodName("save");
        return writer;
    }

/*    @Bean
    public ItemProcessor<Customer, Customer> processor() {
        return new ItemProcessor<Customer,Customer>() {

            @Override
            public Customer process(final Customer customer) throws Exception {
                return customer;
            }
        };
    }*/
    @Bean
    public StudentProcessor processor() {
        return new StudentProcessor();
    }

    @Bean
    public Step step1(ItemReader<Student> itemReader, ItemWriter<Student> itemWriter) {

        return this.stepBuilderFactory.get("step1")
                .<Student, Student>chunk(5)
                .reader(reader())
                .processor(processor())
                .writer(itemWriter)
                .build();
    }

    @Bean
    public Job personUpdateJob(JobCompletionNotificationListener listener, Step step1) {

        return this.jobBuilderFactory.get("personUpdateJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .start(step1)
                .build();
    }

}
