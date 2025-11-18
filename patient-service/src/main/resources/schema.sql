create table if not exists `patients` (
  `id` uuid not null comment 'Unique identifier for the patient',
  `name` varchar(255) not null comment 'Full name of the patient',
  `address` varchar(500) comment 'Residential address of the patient',
  `date_of_birth` date not null comment 'Date of birth of the patient',
  `gender` varchar(50) comment 'Gender of the patient',
  `phone_number` varchar(20) comment 'Contact phone number of the patient',
  `email` varchar(255) comment 'Email address of the patient',
  `registration_date` timestamp default current_timestamp comment 'Date when the patient registered',
  `created_at` timestamp default current_timestamp comment 'Record creation timestamp',
  `updated_at` timestamp default current_timestamp on update current_timestamp comment 'Record last update timestamp',
  constraint `pk_patients` primary key (`id`)
) comment 'Table to store patient information';